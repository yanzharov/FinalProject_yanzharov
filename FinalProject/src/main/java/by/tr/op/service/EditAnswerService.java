package by.tr.op.service;

import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;

public interface EditAnswerService {
    
    void addAnswer(String answerText,int userId,int questionId,int pollId) throws ServiceException,EmptyTextException,WrongIdException;
    
}
