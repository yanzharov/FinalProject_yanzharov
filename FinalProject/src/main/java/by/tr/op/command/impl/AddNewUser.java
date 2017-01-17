package by.tr.op.command.impl;

import by.tr.op.bean.User;
import by.tr.op.command.Command;
import by.tr.op.service.EditUserService;
import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.LoginRegexpException;
import by.tr.op.service.exception.PasswordRegexpException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.factory.ServiceFactory;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddNewUser implements Command{
    private final static String NAME="name";
    private final static String LOGIN="login";
    private final static String PASSWORD="password";
    private final static String ROLE="role";
    private final static String USER="user";
    private final static String ACTIVE="active";
    private final static String REQUEST_ERROR_MESSAGE="errorMessage";
    private final static String EMPTY_TEXT_ERROR_MESSAGE="local.empty_user_text_error_message";
    private final static String LOGIN_REGEXP_ERROR_MESSAGE="local.login_regexp_error_message";
    private final static String PASSWORD_REGEXP_ERROR_MESSAGE="local.password_regexp_error_message";
    private final static String ERROR_MESSAGE="local.user_error_message";
    private final static String PAGE_REGISTRATION="jsp/registration.jsp";
    private final static String PAGE_INDEX="index.jsp";
    private final static String EDIT_USER_SERVICE="EDIT_USER_SERVICE";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        HttpSession session=request.getSession();
        ServiceFactory factory=ServiceFactory.getInstance();
        EditUserService editUserService=(EditUserService)factory.getService(EDIT_USER_SERVICE);
        
        String name=request.getParameter(NAME);
        char[] login=request.getParameter(LOGIN).toCharArray();
        char[] password=request.getParameter(PASSWORD).toCharArray();
        String role=request.getParameter(ROLE);
        
        try{
            user=editUserService.addNewUser(name,login,password,role); 
            
            if(user!=null){
                session.setAttribute(USER, user);
                session.setAttribute(ACTIVE, true);
                session.removeAttribute(REQUEST_ERROR_MESSAGE);
            }
            
        }
        catch(ServiceException e){
            session.setAttribute(REQUEST_ERROR_MESSAGE, ERROR_MESSAGE);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_REGISTRATION);
            requestDispatcher.forward(request, response);
        } 
        catch (EmptyTextException ex) {
            session.setAttribute(REQUEST_ERROR_MESSAGE, EMPTY_TEXT_ERROR_MESSAGE);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_REGISTRATION);
            requestDispatcher.forward(request, response);
        } 
        catch (LoginRegexpException ex) {
            session.setAttribute(REQUEST_ERROR_MESSAGE, LOGIN_REGEXP_ERROR_MESSAGE);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_REGISTRATION);
            requestDispatcher.forward(request, response);
        } 
        catch (PasswordRegexpException ex) {
            session.setAttribute(REQUEST_ERROR_MESSAGE, PASSWORD_REGEXP_ERROR_MESSAGE);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_REGISTRATION);
            requestDispatcher.forward(request, response);
        }
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_INDEX);
	requestDispatcher.forward(request, response);
    }
    
}
