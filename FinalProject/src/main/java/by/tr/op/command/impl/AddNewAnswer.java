package by.tr.op.command.impl;

import by.tr.op.bean.User;
import by.tr.op.command.Command;
import by.tr.op.service.EditAnswerService;
import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import by.tr.op.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddNewAnswer implements Command{
    private final static String ANSWER_TEXT="answerText";
    private final static String QUESTION_ID="questionId";
    private final static String USER="user";
    private final static String ACTIVE="active";
    private final static String REQUEST_ERROR_MESSAGE="errorMessage";
    private final static String ERROR_MESSAGE="local.answer_error_message";
    private final static String EMPTY_TEXT_ERROR_MESSAGE="local.empty_answer_text_error_message";
    private final static String WRONG_ID_MESSAGE="local.wrong_id_message";
    private final static String GET_POLL_BY_ID="Controller?commandName=GET_POLL_BY_ID&id=";
    private final static String POLL_ID="pollId";
    private final static String EDIT_ANSWER_SERVICE="EDIT_ANSWER_SERVICE";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        ServiceFactory factory=ServiceFactory.getInstance();
        EditAnswerService editAnswerService=(EditAnswerService)factory.getService(EDIT_ANSWER_SERVICE);
        
        String answerText=request.getParameter(ANSWER_TEXT);
        int questionId=Integer.valueOf(request.getParameter(QUESTION_ID));
        int pollId=Integer.valueOf(request.getParameter(POLL_ID));
        int userId=-1;
        User user=(User)session.getAttribute(USER);
        Boolean active=(Boolean)session.getAttribute(ACTIVE);
        
        if(user!=null && active==true){
            userId=user.getId();
  
            try{
                editAnswerService.addAnswer(answerText, userId, questionId, pollId);
            }
            catch(ServiceException e){
                request.setAttribute(REQUEST_ERROR_MESSAGE, ERROR_MESSAGE);
            } 
            catch (EmptyTextException ex) {
                request.setAttribute(REQUEST_ERROR_MESSAGE, EMPTY_TEXT_ERROR_MESSAGE);
            } 
            catch (WrongIdException ex) {
                request.setAttribute(REQUEST_ERROR_MESSAGE, WRONG_ID_MESSAGE);
            }
        
        }
        else{
            request.setAttribute(REQUEST_ERROR_MESSAGE, ERROR_MESSAGE);
        }
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(GET_POLL_BY_ID+pollId);
	requestDispatcher.forward(request, response);
    }
    
}
