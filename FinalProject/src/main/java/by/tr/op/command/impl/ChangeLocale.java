package by.tr.op.command.impl;

import by.tr.op.command.Command;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocale implements Command{
    private final static String PAGE_INDEX="index.jsp";
    private final static String LOCAL="local";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter(LOCAL)!=null && !request.getParameter(LOCAL).equals("")){
            request.getSession().setAttribute(LOCAL,request.getParameter(LOCAL));
        }
  
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_INDEX);
	requestDispatcher.forward(request, response);
    }
    
}
