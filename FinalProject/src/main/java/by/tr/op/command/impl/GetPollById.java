package by.tr.op.command.impl;

import by.tr.op.bean.Poll;
import by.tr.op.bean.Question;
import by.tr.op.command.Command;
import by.tr.op.service.FindPollService;
import by.tr.op.service.FindQuestionService;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import by.tr.op.service.factory.ServiceFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetPollById implements Command{
    private final static String ID="id";
    private final static String POLL="poll";
    private final static String POLLS="polls";
    private final static String QUESTIONS="questions";
    private final static String REQUEST_ERROR_MESSAGE="errorMessage";
    private final static String GET_POLL_ERROR_MESSAGE="local.get_poll_error_message";
    private final static String WRONG_ID_MESSAGE="local.wrong_id_message";
    private final static String GET_POLLS_ERROR_MESSAGE="local.get_polls_error_message";
    private final static String GET_QUESTIONS_ERROR_MESSAGE="local.get_questions_error_message";
    private final static String PAGE_SINGLE_POLL="jsp/singlepoll.jsp";
    private final static String FIND_QUESTION_SERVICE="FIND_QUESTION_SERVICE";
    private final static String FIND_POLL_SERVICE="FIND_POLL_SERVICE";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory factory=ServiceFactory.getInstance();
        FindPollService findPollService=(FindPollService)factory.getService(FIND_POLL_SERVICE);
        FindQuestionService findQuestionService=(FindQuestionService)factory.getService(FIND_QUESTION_SERVICE);
        Poll poll;
        List<Poll> polls;
        List<Question> questions;
        
        try{
            poll=findPollService.getPollById(Integer.valueOf(request.getParameter(ID)));
            request.setAttribute(POLL, poll);
        }
        catch(ServiceException e){
            request.setAttribute(REQUEST_ERROR_MESSAGE, GET_POLL_ERROR_MESSAGE);
        }
        catch (WrongIdException ex) {
            request.setAttribute(REQUEST_ERROR_MESSAGE, WRONG_ID_MESSAGE);
        } 
        
        try{
            polls=findPollService.getPolls();
            request.setAttribute(POLLS, polls);
        }
        catch(ServiceException e){
            request.setAttribute(REQUEST_ERROR_MESSAGE, GET_POLLS_ERROR_MESSAGE);
        }
        
        try{
            questions=findQuestionService.getQuestionsByPollId(Integer.valueOf(request.getParameter(ID)));
            request.setAttribute(QUESTIONS, questions);
        }
        catch(ServiceException e){
            request.setAttribute(REQUEST_ERROR_MESSAGE, GET_QUESTIONS_ERROR_MESSAGE);
        }
        catch (WrongIdException ex) {
            request.setAttribute(REQUEST_ERROR_MESSAGE, WRONG_ID_MESSAGE);
        } 
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_SINGLE_POLL);
	requestDispatcher.forward(request, response);
    }
}
