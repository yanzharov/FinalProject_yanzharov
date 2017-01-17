package by.tr.op.service;

import by.tr.op.bean.AnswerResult;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import java.util.List;

public interface FindAnswerService {
    
   List<AnswerResult> getAnswersAndResultsByQuestionId(int questionId) throws ServiceException,WrongIdException; 
   
}
