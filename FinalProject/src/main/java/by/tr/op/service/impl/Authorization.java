package by.tr.op.service.impl;

import by.tr.op.bean.User;
import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.UserDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.AuthorizationService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.LoginRegexpException;
import by.tr.op.service.exception.PasswordRegexpException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.scrambler.HashScrambler;

public class Authorization implements AuthorizationService,Service{
    private final static String LOGIN_REGEXP="^[a-zA-Z0-9]+$";
    private final static String PASSWORD_REGEXP="^[a-zA-Z0-9]+$";
    private final static String USER_DAO="USER_DAO";

    @Override
    public User signIn(char[] login, char[] password) throws ServiceException, LoginRegexpException, PasswordRegexpException {
        User user;
        
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
            user=userDao.signIn(login, hashPassword);
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
        return user;
    }    
    
}
