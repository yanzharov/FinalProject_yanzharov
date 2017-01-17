package by.tr.op.service;

import by.tr.op.bean.User;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import java.util.List;

public interface FindUserService {
    
    boolean isUserAnswered(int userId,int questionId) throws ServiceException,WrongIdException;
    List<User> getUsers() throws ServiceException;
    
}
