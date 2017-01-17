package by.tr.op.service.impl;

import by.tr.op.dao.AnswerDAO;
import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.PollsDAO;
import by.tr.op.dao.QuestionDAO;
import by.tr.op.dao.UserDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.EditAnswerService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;

public class EditAnswer implements EditAnswerService,Service{
    private final static String USER_DAO="USER_DAO";
    private final static String POLLS_DAO="POLLS_DAO";
    private final static String ANSWER_DAO="ANSWER_DAO";
    private final static String QUESTION_DAO="QUESTION_DAO";

    @Override
    public void addAnswer(String answerText, int userId, int questionId, int pollId) throws ServiceException, EmptyTextException, WrongIdException {
        
        if (answerText == null || "".equals(answerText)){
            throw new EmptyTextException();
	}
        
        if (userId<0){
            throw new WrongIdException();
	}
        
        if (questionId<0){
            throw new WrongIdException();
	}
        
        if (pollId<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        AnswerDAO answerDao=(AnswerDAO)factory.getDAOProvider(ANSWER_DAO);
        UserDAO userDao=(UserDAO)factory.getDAOProvider(USER_DAO);
        QuestionDAO questionDao=(QuestionDAO)factory.getDAOProvider(QUESTION_DAO);
        PollsDAO pollsDao=(PollsDAO)factory.getDAOProvider(POLLS_DAO);
        
        try{
            int count=answerDao.getAnswerCount();
            boolean checkQuestion=userDao.isUserAnswered(userId, questionId);

            if(checkQuestion==false){
                answerDao.addNewAnswer(count+1, answerText, userId, questionId);
                questionDao.addUserAndQuestionConnection(userId, questionId);
                boolean checkPoll=pollsDao.isUserAnswered(userId, pollId);
                    
                if(checkPoll==false){
                    pollsDao.addPollAndUserConnect(userId, pollId);
                }
                             
            }
     
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
}
