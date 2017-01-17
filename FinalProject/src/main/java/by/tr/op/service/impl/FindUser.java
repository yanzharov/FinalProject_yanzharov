package by.tr.op.service.impl;

import by.tr.op.bean.User;
import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.UserDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.FindUserService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import java.util.List;

public class FindUser implements FindUserService,Service{
    private final static String USER_DAO="USER_DAO";

    @Override
    public boolean isUserAnswered(int userId, int questionId) throws ServiceException, WrongIdException {
        
        if (questionId<0){
            throw new WrongIdException();
	}
        
        if (userId<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        UserDAO userDao=(UserDAO)factory.getDAOProvider(USER_DAO);
        boolean answered=false;
        
        try{
            answered=userDao.isUserAnswered(userId, questionId);
            return answered;
        } 
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }

    @Override
    public List<User> getUsers() throws ServiceException {
        DAOFactory factory=DAOFactory.getInstance();
        UserDAO userDao=(UserDAO)factory.getDAOProvider(USER_DAO);
        List<User> users;
        
        try{
            users=userDao.getUsers();
            return users;
        } 
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
}
