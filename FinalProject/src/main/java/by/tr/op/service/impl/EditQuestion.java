package by.tr.op.service.impl;

import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.QuestionDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.EditQuestionService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;

public class EditQuestion implements EditQuestionService,Service{
    private final static String QUESTION_DAO="QUESTION_DAO";
    
    @Override
    public void deleteQuestion(int id) throws ServiceException, WrongIdException {
        
        if (id<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        QuestionDAO questionDao=(QuestionDAO)factory.getDAOProvider(QUESTION_DAO);
        
        try{
            questionDao.deleteQuestion(id);
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }

    @Override
    public int addQuestion(String questionText, int opinionPollId, int authorId) throws ServiceException, EmptyTextException, WrongIdException {
        int count;
        
        if (questionText == null || "".equals(questionText)){
            throw new EmptyTextException();
	}
        
        if (opinionPollId<0){
            throw new WrongIdException();
	}
        
        if (authorId<0){
            throw new WrongIdException();
	}
               
        DAOFactory factory=DAOFactory.getInstance();
        QuestionDAO questionDao=(QuestionDAO)factory.getDAOProvider(QUESTION_DAO);
        
        try{
            count=questionDao.getQuestionCount();
            questionDao.addQuestion(count+1, questionText, opinionPollId, authorId);     
            return count+1;      
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
}
