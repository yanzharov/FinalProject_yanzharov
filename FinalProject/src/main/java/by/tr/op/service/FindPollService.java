package by.tr.op.service;

import by.tr.op.bean.Poll;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import java.util.List;

public interface FindPollService {
    
    List<Poll> getPolls() throws ServiceException;
    Poll getPollById(int id) throws ServiceException,WrongIdException;
    List<Poll> getPopularPolls() throws ServiceException;
    
}
