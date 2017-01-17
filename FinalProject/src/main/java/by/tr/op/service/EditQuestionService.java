package by.tr.op.service;

import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;

public interface EditQuestionService{
    
    int addQuestion(String questionText, int opinionPollId, int authorId) throws ServiceException,EmptyTextException,WrongIdException;
    void deleteQuestion(int id) throws ServiceException,WrongIdException;
    
}
