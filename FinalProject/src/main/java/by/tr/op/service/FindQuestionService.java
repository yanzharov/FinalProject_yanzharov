package by.tr.op.service;

import by.tr.op.bean.Question;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import java.util.List;


public interface FindQuestionService {
    
    List<Question> getQuestionsByPollId(int pollId) throws ServiceException,WrongIdException;
    Question getQuestionById(int id) throws ServiceException,WrongIdException;
    
}
