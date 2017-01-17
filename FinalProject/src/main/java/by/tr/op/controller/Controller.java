package by.tr.op.controller;

import by.tr.op.command.Command;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {
    private final static String COMMAND_NAME="commandName";
    private final static String CONTROLLER="Controller";
    private final static String ERROR_PAGE="jsp/errorpage.jsp";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName;
        final CommandProvider provider = CommandProvider.getInstance();
        commandName=request.getParameter(COMMAND_NAME);
        
        try{
            Command command = provider.getCommand(commandName); 
            command.execute(request,response);
        }
        catch(NullPointerException e){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
        
    }

    @Override
    public String getServletInfo() {
        return CONTROLLER;
    }

}
