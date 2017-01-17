package by.tr.op.service.impl;

import by.tr.op.bean.Question;
import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.QuestionDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.FindQuestionService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import java.util.List;

public class FindQuestion implements FindQuestionService,Service{
    private final static String QUESTION_DAO="QUESTION_DAO";   

    @Override
    public List<Question> getQuestionsByPollId(int pollId) throws ServiceException, WrongIdException {
        
        if (pollId<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        QuestionDAO questionDao=(QuestionDAO)factory.getDAOProvider(QUESTION_DAO);
        List<Question> list;
        
        try{
            list=questionDao.getQuestionsByPollId(pollId);
            return list;
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }

    @Override
    public Question getQuestionById(int id) throws ServiceException, WrongIdException {
        
        if (id<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        QuestionDAO questionDao=(QuestionDAO)factory.getDAOProvider(QUESTION_DAO);
        Question question;
        
        try{
            question=questionDao.getQuestionById(id);
            return question;
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
}
