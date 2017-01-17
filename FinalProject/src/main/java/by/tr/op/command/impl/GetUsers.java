package by.tr.op.command.impl;

import by.tr.op.bean.User;
import by.tr.op.command.Command;
import by.tr.op.service.FindUserService;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.factory.ServiceFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class GetUsers implements Command{
    private final static String ACTIVE="active";
    private final static String USER="user";
    private final static String USERS="users";
    private final static String ADMIN="admin";
    private final static String REQUEST_ERROR_MESSAGE="errorMessage";
    private final static String USER_ERROR_MESSAGE="local.users_information_error_message";
    private final static String PAGE_USERS="jsp/users.jsp";
    private final static String FIND_USER_SERVICE="FIND_USER_SERVICE";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        ServiceFactory factory=ServiceFactory.getInstance();
        FindUserService findUserService=(FindUserService)factory.getService(FIND_USER_SERVICE);
        List<User> users;
    
        User user=(User)session.getAttribute(USER);
        Boolean active=(Boolean)session.getAttribute(ACTIVE);
        
        if(user!=null && active==true && user.getRole().equals(ADMIN)){
            
            try{
                users=findUserService.getUsers();
                request.setAttribute(USERS, users);
            }
            catch(ServiceException e){
                request.setAttribute(REQUEST_ERROR_MESSAGE, USER_ERROR_MESSAGE);
            }
                    
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_USERS);
	requestDispatcher.forward(request, response);
    }
    
}
