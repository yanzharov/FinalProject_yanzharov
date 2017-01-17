package by.tr.op.service.impl;

import by.tr.op.bean.AnswerResult;
import by.tr.op.dao.AnswerDAO;
import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.FindAnswerService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import java.util.List;

public class FindAnswer implements FindAnswerService,Service{
    private final static String ANSWER_DAO="ANSWER_DAO";

    @Override
    public List<AnswerResult> getAnswersAndResultsByQuestionId(int questionId) throws ServiceException, WrongIdException {
        
        if (questionId<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        AnswerDAO answerDao=(AnswerDAO)factory.getDAOProvider(ANSWER_DAO);
        List<AnswerResult> list;
        
        try{
            list=answerDao.getAnswersAndResultsByQuestionId(questionId);
            int count=0;
            int totalAnswerCount=0;
            
            for(AnswerResult answerResult:list){
                count+=answerResult.getCount();
            }
            
            for(AnswerResult answerResult:list){
                totalAnswerCount=answerResult.getCount()*100/count;
                answerResult.setCount(totalAnswerCount);
            }
            
            return list;
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
       
}
