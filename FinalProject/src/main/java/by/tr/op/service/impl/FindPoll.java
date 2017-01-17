package by.tr.op.service.impl;

import by.tr.op.bean.Poll;
import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.PollsDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.FindPollService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import java.util.List;

public class FindPoll implements FindPollService,Service{
    private final static String POLLS_DAO="POLLS_DAO";

    @Override
    public List<Poll> getPolls() throws ServiceException {
        DAOFactory factory=DAOFactory.getInstance();
        PollsDAO pollsDao=(PollsDAO)factory.getDAOProvider(POLLS_DAO);
        List<Poll> list;
        
        try{
            list=pollsDao.getPolls();
            return list;
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }

    @Override
    public Poll getPollById(int id) throws ServiceException, WrongIdException {
        
        if (id<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        PollsDAO pollsDao=(PollsDAO)factory.getDAOProvider(POLLS_DAO);
        Poll poll;
        
        try{
            poll=pollsDao.getPollById(id);
            return poll;
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }

    @Override
    public List<Poll> getPopularPolls() throws ServiceException {
        DAOFactory factory=DAOFactory.getInstance();
        PollsDAO pollsDao=(PollsDAO)factory.getDAOProvider(POLLS_DAO);
        List<Poll> list;
        
        try{
            list=pollsDao.getPopularPolls();
            return list;
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
}
