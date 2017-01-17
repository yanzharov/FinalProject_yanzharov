package by.tr.op.command.impl;

import by.tr.op.command.Command;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOut implements Command{
    private final static String ACTIVE="active";
    private final static String USER="user";
    private final static String PAGE_INDEX="index.jsp";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        
        session.removeAttribute(USER);
        session.setAttribute(ACTIVE, false);
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_INDEX);
	requestDispatcher.forward(request, response);
    }
    
}
