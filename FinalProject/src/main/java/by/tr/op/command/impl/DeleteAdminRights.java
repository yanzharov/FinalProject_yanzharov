package by.tr.op.command.impl;

import by.tr.op.bean.User;
import by.tr.op.command.Command;
import by.tr.op.service.EditUserService;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import by.tr.op.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteAdminRights implements Command{
    private final static String ACTIVE="active";
    private final static String USER="user";
    private final static String ID="id";
    private final static String ROLE="role";
    private final static String ADMIN="admin";
    private final static String REQUEST_ERROR_MESSAGE="errorMessage";
    private final static String WRONG_ID_MESSAGE="local.wrong_id_message";
    private final static String USER_ERROR_MESSAGE="local.user_state_error_message";
    private final static String PAGE_USERS="Controller?commandName=GET_USERS";
    private final static String EDIT_USER_SERVICE="EDIT_USER_SERVICE";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        ServiceFactory factory=ServiceFactory.getInstance();
        EditUserService editUserService=(EditUserService)factory.getService(EDIT_USER_SERVICE);
    
        User user=(User)session.getAttribute(USER);
        Boolean active=(Boolean)session.getAttribute(ACTIVE);
        int id=Integer.valueOf(request.getParameter(ID));
        String role=request.getParameter(ROLE);
        
        if(user!=null && active==true && user.getRole().equals(ADMIN) && role.equals(ADMIN)){
            
            try{
                editUserService.deleteAdminRights(id);
            }
            catch(ServiceException e){
                request.setAttribute(REQUEST_ERROR_MESSAGE, USER_ERROR_MESSAGE);
            }
            catch (WrongIdException ex) {
                request.setAttribute(REQUEST_ERROR_MESSAGE, WRONG_ID_MESSAGE);
            } 
                    
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_USERS);
	requestDispatcher.forward(request, response);
    }
    
}
