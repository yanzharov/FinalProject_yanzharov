package by.tr.op.command.impl;

import by.tr.op.bean.User;
import by.tr.op.command.Command;
import by.tr.op.service.EditPollService;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import by.tr.op.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeletePoll implements Command{
    private final static String ID="id";
    private final static String ACTIVE="active";
    private final static String USER="user";
    private final static String REQUEST_ERROR_MESSAGE="errorMessage";
    private final static String WRONG_ID_MESSAGE="local.wrong_id_message";
    private final static String ERROR_MESSAGE="local.poll_edit_error_message";
    private final static String PAGE_POLLS="Controller?commandName=GET_POLLS&pageRedirect=jsp/polls.jsp";
    private final static String EDIT_POLL_SERVICE="EDIT_POLL_SERVICE";
    private final static String ADMIN="admin";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        ServiceFactory factory=ServiceFactory.getInstance();
        EditPollService editPollService=(EditPollService)factory.getService(EDIT_POLL_SERVICE);
        
        int id=Integer.valueOf(request.getParameter(ID));
        User user=(User)session.getAttribute(USER);
        Boolean active=(Boolean)session.getAttribute(ACTIVE);
        
        if(user!=null && active==true && user.getRole().equals(ADMIN)){
            
            try{
                editPollService.deletePoll(id);       
            }
            catch(ServiceException e){
                request.setAttribute(REQUEST_ERROR_MESSAGE, ERROR_MESSAGE);
            }
            catch (WrongIdException ex) {
                request.setAttribute(REQUEST_ERROR_MESSAGE, WRONG_ID_MESSAGE);
            } 
        
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_POLLS);
	requestDispatcher.forward(request, response);
    }
    
}
