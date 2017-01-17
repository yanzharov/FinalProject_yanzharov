package by.tr.op.service;

import by.tr.op.bean.User;
import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.LoginRegexpException;
import by.tr.op.service.exception.PasswordRegexpException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;

public interface EditUserService {
    
    User addNewUser(String name, char[] login, char[] password, String role) throws ServiceException,EmptyTextException,LoginRegexpException,PasswordRegexpException;
    void setAdmin(int id) throws ServiceException,WrongIdException;
    void deleteAdminRights(int id) throws ServiceException,WrongIdException;
    
}
