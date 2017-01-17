
package by.tr.op.command.impl;

import by.tr.op.bean.Poll;
import by.tr.op.command.Command;
import by.tr.op.service.FindPollService;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.factory.ServiceFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetPolls implements Command{
    private final static String POLLS="polls";
    private final static String REQUEST_ERROR_MESSAGE="errorMessage";
    private final static String ERROR_MESSAGE="local.get_polls_error_message";
    private final static String PAGE_REDIRECT="pageRedirect";
    private final static String FIND_POLL_SERVICE="FIND_POLL_SERVICE";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory factory=ServiceFactory.getInstance();
        FindPollService findPollService=(FindPollService)factory.getService(FIND_POLL_SERVICE);
        List<Poll> polls;
        
        try{
            polls=findPollService.getPolls();
            request.setAttribute(POLLS, polls);
        }
        catch(ServiceException e){
            request.setAttribute(REQUEST_ERROR_MESSAGE, ERROR_MESSAGE);
        }
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getParameter(PAGE_REDIRECT));
	requestDispatcher.forward(request, response);
    }
    
}
