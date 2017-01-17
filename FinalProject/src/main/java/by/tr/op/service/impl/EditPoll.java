package by.tr.op.service.impl;

import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.PollsDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.EditPollService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import by.tr.op.service.exception.WrongStateException;

public class EditPoll implements EditPollService,Service{
    private final static String ACTIVE="active";
    private final static String CLOSED="closed";
    private final static String POLLS_DAO="POLLS_DAO";

    @Override
    public void deletePoll(int id) throws ServiceException, WrongIdException {
        
        if (id<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        PollsDAO pollsDao=(PollsDAO)factory.getDAOProvider(POLLS_DAO);
        
        try{
            pollsDao.deletePoll(id);
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }

    @Override
    public void updatePoll(int id, String theme, String state, String image) throws ServiceException, EmptyTextException, WrongStateException, WrongIdException {
       
        if (theme == null || "".equals(theme)){
            throw new EmptyTextException();
	}
        
        if (image == null || "".equals(image)){
            throw new EmptyTextException();
	}

        if(!state.equals(ACTIVE) && !state.equals(CLOSED)){
            throw new WrongStateException();
        }
        
        if (id<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        PollsDAO pollsDao=(PollsDAO)factory.getDAOProvider(POLLS_DAO);
        
        try{
            pollsDao.editPoll(id, theme, state, image);
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }

    @Override
    public void addPoll(String theme, int authorId, String state, String image) throws ServiceException, EmptyTextException, WrongStateException, WrongIdException {
        
        if (theme == null || "".equals(theme)){
            throw new EmptyTextException();
	}
        
        if (image == null || "".equals(image)){
            throw new EmptyTextException();
	}
        
        if(!state.equals(ACTIVE) && !state.equals(CLOSED)){
            throw new WrongStateException();
        }
        
        if (authorId<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        PollsDAO pollsDao=(PollsDAO)factory.getDAOProvider(POLLS_DAO);
        
        try{
            int count=pollsDao.getPollCount();
            pollsDao.addPoll(count+1, theme, authorId, state, image);       
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
}