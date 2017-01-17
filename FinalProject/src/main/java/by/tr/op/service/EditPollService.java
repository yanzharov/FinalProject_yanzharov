package by.tr.op.service;

import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import by.tr.op.service.exception.WrongStateException;

public interface EditPollService {
    
    void updatePoll(int id, String theme, String state, String image) throws ServiceException,EmptyTextException,WrongStateException,WrongIdException;
    void deletePoll(int id) throws ServiceException,WrongIdException;
    void addPoll(String theme, int authorId,String state,String image) throws ServiceException,EmptyTextException,WrongStateException,WrongIdException;
    
}
