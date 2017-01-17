package by.tr.op.service.impl;

import by.tr.op.bean.User;
import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.UserDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.EditUserService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.LoginRegexpException;
import by.tr.op.service.exception.PasswordRegexpException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import by.tr.op.service.scrambler.HashScrambler;

public class EditUser implements EditUserService,Service{
    private final static String LOGIN_REGEXP="^[a-zA-Z0-9]+$";
    private final static String PASSWORD_REGEXP="^[a-zA-Z0-9]+$";
    private final static String USER_DAO="USER_DAO";

    @Override
    public User addNewUser(String name, char[] login, char[] password, String role) throws ServiceException, EmptyTextException, LoginRegexpException, PasswordRegexpException {
        User user=new User();
        
        if (name == null || "".equals(name)){
            throw new EmptyTextException();
	}
        
        if(!String.valueOf(login).matches(LOGIN_REGEXP)){
            throw new LoginRegexpException();
        }
        
        if(!String.valueOf(password).matches(PASSWORD_REGEXP)){
            throw new PasswordRegexpException();
        }
        
        HashScrambler hashScrambler=HashScrambler.getInstance();
        int hashPassword=hashScrambler.encrypt(password);
        DAOFactory factory=DAOFactory.getInstance();
        UserDAO userDao=(UserDAO)factory.getDAOProvider(USER_DAO);
        
        try{
            int count=userDao.getUserCount();  
            userDao.addNewUser(count+1, name, login, hashPassword, role);
            user.setId(count+1);
            user.setName(name);
            user.setRole(role);
            return user;
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }

    @Override
    public void setAdmin(int id) throws ServiceException, WrongIdException {
        
        if (id<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        UserDAO userDao=(UserDAO)factory.getDAOProvider(USER_DAO);
        
        try{
            userDao.setAdmin(id);
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }

    @Override
    public void deleteAdminRights(int id) throws ServiceException, WrongIdException {
        
        if (id<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        UserDAO userDao=(UserDAO)factory.getDAOProvider(USER_DAO);
        
        try{
            userDao.deleteAdminRights(id);
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
}
