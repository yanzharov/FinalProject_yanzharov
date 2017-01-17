package by.tr.op.command.impl;

import by.tr.op.bean.User;
import by.tr.op.command.Command;
import by.tr.op.service.EditQuestionService;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import by.tr.op.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteQuestion implements Command{
    private final static String QUESTION_ID="questionId";
    private final static String OPINION_POLL_ID="opinionPollId";
    private final static String ACTIVE="active";
    private final static String USER="user";
    private final static String REQUEST_ERROR_MESSAGE="errorMessage";
    private final static String WRONG_ID_MESSAGE="local.wrong_id_message";
    private final static String ERROR_MESSAGE="local.question_delete_error_message";
    private final static String SINGLE_POLL="Controller?commandName=GET_POLL_BY_ID&id=";
    private final static String EDIT_QUESTION_SERVICE="EDIT_QUESTION_SERVICE";
    private final static String ADMIN="admin";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        ServiceFactory factory=ServiceFactory.getInstance();
        EditQuestionService editQuestionService=(EditQuestionService)factory.getService(EDIT_QUESTION_SERVICE);
        
        int questionId=Integer.valueOf(request.getParameter(QUESTION_ID));
        int opinionPollId=Integer.valueOf(request.getParameter(OPINION_POLL_ID));
        int authorId=-1;
        User user=(User)session.getAttribute(USER);
        Boolean active=(Boolean)session.getAttribute(ACTIVE);
            
        if(user!=null && active==true && user.getRole().equals(ADMIN)){
            authorId=user.getId();

            try{
                editQuestionService.deleteQuestion(questionId);             
            }
            catch(ServiceException e){
                request.setAttribute(REQUEST_ERROR_MESSAGE, ERROR_MESSAGE);
            }
            catch (WrongIdException ex) {
                request.setAttribute(REQUEST_ERROR_MESSAGE, WRONG_ID_MESSAGE);
            } 
            
        }
        else{
            request.setAttribute(REQUEST_ERROR_MESSAGE, ERROR_MESSAGE);
        }
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(SINGLE_POLL+opinionPollId);
	requestDispatcher.forward(request, response);
    }
    
}
