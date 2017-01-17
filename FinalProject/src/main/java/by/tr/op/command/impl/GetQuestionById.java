package by.tr.op.command.impl;

import by.tr.op.bean.AnswerResult;
import by.tr.op.bean.Option;
import by.tr.op.bean.Question;
import by.tr.op.bean.User;
import by.tr.op.command.Command;
import by.tr.op.service.FindAnswerService;
import by.tr.op.service.FindOptionService;
import by.tr.op.service.FindQuestionService;
import by.tr.op.service.FindUserService;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import by.tr.op.service.factory.ServiceFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetQuestionById implements Command{
    private final static String ID="id";
    private final static String POLL_STATE="pollState";
    private final static String ANSWERED="answered";
    private final static String QUESTION="question";
    private final static String OPTIONS="options";
    private final static String ANSWER_RESULTS="answerResults";
    private final static String ACTIVE="active";
    private final static String USER="user";
    private final static String REQUEST_ERROR_MESSAGE="errorMessage";
    private final static String USER_ERROR_MESSAGE="local.user_information_error_message";
    private final static String QUESTION_ERROR_MESSAGE="local.get_question_error_message";
    private final static String WRONG_ID_MESSAGE="local.wrong_id_message";
    private final static String OPTIONS_ERROR_MESSAGE="local.get_options_error_message";
    private final static String RESULTS_ERROR_MESSAGE="local.get_results_error_message";
    private final static String PAGE_SINGLE_QUESTION="jsp/singlequestion.jsp";
    private final static String FIND_QUESTION_SERVICE="FIND_QUESTION_SERVICE";
    private final static String FIND_OPTION_SERVICE="FIND_OPTION_SERVICE";
    private final static String FIND_ANSWER_SERVICE="FIND_ANSWER_SERVICE";
    private final static String FIND_USER_SERVICE="FIND_USER_SERVICE";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        ServiceFactory factory=ServiceFactory.getInstance();
        FindQuestionService findQuestionService=(FindQuestionService)factory.getService(FIND_QUESTION_SERVICE);
        FindOptionService findOptionService=(FindOptionService)factory.getService(FIND_OPTION_SERVICE);
        FindUserService findUserService=(FindUserService)factory.getService(FIND_USER_SERVICE);
        FindAnswerService findAnswerService=(FindAnswerService)factory.getService(FIND_ANSWER_SERVICE);
        Question question;
        List<Option> options;
        List<AnswerResult> answerResults;
        
        int userId=-1;
        boolean answered=false;
        User user=(User)session.getAttribute(USER);
        Boolean active=(Boolean)session.getAttribute(ACTIVE);
        String pollState=request.getParameter(POLL_STATE);
        request.setAttribute(POLL_STATE, pollState);
        
        if(user!=null && active==true){
            userId=user.getId();
            
            try{
                answered=findUserService.isUserAnswered(userId, Integer.valueOf(request.getParameter(ID)));
                request.setAttribute(ANSWERED, answered);
            }
            catch(ServiceException e){
                request.setAttribute(REQUEST_ERROR_MESSAGE, USER_ERROR_MESSAGE);
            }
            catch (WrongIdException ex) {
                request.setAttribute(REQUEST_ERROR_MESSAGE, WRONG_ID_MESSAGE);
            } 
            
            try{
                question=findQuestionService.getQuestionById(Integer.valueOf(request.getParameter(ID)));
                request.setAttribute(QUESTION, question);
            }
            catch(ServiceException e){
                request.setAttribute(REQUEST_ERROR_MESSAGE, QUESTION_ERROR_MESSAGE);
            }
            catch (WrongIdException ex) {
                request.setAttribute(REQUEST_ERROR_MESSAGE, WRONG_ID_MESSAGE);
            } 
            
            if(answered==false && pollState.equals(ACTIVE)){
                
                try{
                    options=findOptionService.getOptionsByQuestionId(Integer.valueOf(request.getParameter(ID)));
                    request.setAttribute(OPTIONS, options);
                }
                catch(ServiceException e){
                    request.setAttribute(REQUEST_ERROR_MESSAGE, OPTIONS_ERROR_MESSAGE);
                }
                catch (WrongIdException ex) {
                    request.setAttribute(REQUEST_ERROR_MESSAGE, WRONG_ID_MESSAGE);
                } 

            }
            else{
                
                try{
                    answerResults=findAnswerService.getAnswersAndResultsByQuestionId(Integer.valueOf(request.getParameter(ID)));
                    request.setAttribute(ANSWER_RESULTS, answerResults);
                }
                catch(ServiceException e){
                    request.setAttribute(REQUEST_ERROR_MESSAGE, RESULTS_ERROR_MESSAGE);
                }
                catch (WrongIdException ex) {
                    request.setAttribute(REQUEST_ERROR_MESSAGE, WRONG_ID_MESSAGE);
                } 
                
            }
            
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_SINGLE_QUESTION);
	requestDispatcher.forward(request, response);
        
    }
    
}
