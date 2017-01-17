package by.tr.op.dao;

import by.tr.op.bean.User;
import by.tr.op.dao.exception.DAOException;
import java.util.List;

public interface UserDAO {
    
    void addNewUser(int id,String name,char[] login,int password,String role) throws DAOException;
    int getUserCount() throws DAOException;
    boolean isUserAnswered(int userId,int questionId) throws DAOException;
    User signIn(char[] login,int password) throws DAOException;
    List<User> getUsers() throws DAOException;
    void setAdmin(int id) throws DAOException;
    void deleteAdminRights(int id) throws DAOException;
    
}
