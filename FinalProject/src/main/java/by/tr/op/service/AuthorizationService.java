package by.tr.op.service;

import by.tr.op.bean.User;
import by.tr.op.service.exception.LoginRegexpException;
import by.tr.op.service.exception.PasswordRegexpException;
import by.tr.op.service.exception.ServiceException;

public interface AuthorizationService {
    
    User signIn(char[] login,char[] password) throws ServiceException,LoginRegexpException,PasswordRegexpException;
    
}
