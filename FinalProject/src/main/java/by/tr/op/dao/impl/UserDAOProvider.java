package by.tr.op.dao.impl;

import by.tr.op.bean.User;
import by.tr.op.dao.DAOProvider;
import by.tr.op.dao.UserDAO;
import by.tr.op.dao.connection.ConnectionPool;
import by.tr.op.dao.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class UserDAOProvider implements UserDAO,DAOProvider{
    private final static String SELECT_USER_ID_QUERY="SELECT `id`,`name`,`role` FROM `users` WHERE `login`=? AND `password`=?";
    private final static String INSERT_USER_QUERY="INSERT INTO `users`(`id`,`name`,`login`,`password`,`role`) VALUES(?,?,?,?,?)";
    private final static String SELECT_MAX_USER_ID_QUERY="SELECT MAX(`id`) FROM `users`";
    private final static String SELECT_USER_ID_BY_QUESTION_ID="SELECT `user_id` FROM `questions_users_connect` WHERE `user_id`=? AND `question_id`=?";
    private final static String SELECT_USERS="SELECT `id`,`name`,`login`,`role` FROM `users`";
    private final static String SET_ADMIN="UPDATE `users` SET `role`='admin' WHERE id=?";
    private final static String DELETE_ADMIN_RIGHTS="UPDATE `users` SET `role`='user' WHERE id=?";
    private final static String ERROR="PreparedStatement closing error";
    public static final Logger logger = Logger.getLogger(UserDAOProvider.class);
    
    @Override
    public User signIn(char[] login, int password) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        User user=new User();
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();           
            st=con.prepareStatement(SELECT_USER_ID_QUERY);
            
            st.setString(1,String.valueOf(login));
            st.setInt(2, password);
            
            set=st.executeQuery();
            
            if(set.next()){
                user.setId(set.getInt(1));
                user.setName(set.getString(2));
                user.setRole(set.getString(3));
            }
            else{
                throw new DAOException();
            }
            
            return user;       
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        catch(InterruptedException e){
            throw new DAOException(e);
        }
        finally{

            try {
                st.close();
            } 
            catch (SQLException e) {
                logger.error(ERROR);
            }
            
            conPool.putback(con);
        }
        
    }

    @Override
    public void addNewUser(int id, String name, char[] login, int password, String role) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(INSERT_USER_QUERY);
            
            st.setInt(1,id);
            st.setString(2, name);
            st.setString(3, String.valueOf(login));
            st.setInt(4, password);
            st.setString(5,role);
            
            st.executeUpdate();
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        catch(InterruptedException e){
            throw new DAOException(e);
        }
        finally{

            try {
                st.close();
            } 
            catch (SQLException e) {
                logger.error(ERROR);
            }
            
            conPool.putback(con);
        }
        
    }

    @Override
    public int getUserCount() throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        int count=-1;
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_MAX_USER_ID_QUERY);

            set=st.executeQuery();
            
            if(set.next()){
                count=set.getInt(1);
            }
            else{
                throw new DAOException();
            }
            
            return count;
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        catch(InterruptedException e){
            throw new DAOException(e);
        }
        finally{

            try {
                st.close();
            } 
            catch (SQLException e) {
                logger.error(ERROR);
            }
            
            conPool.putback(con);
        }
        
    }
    
    @Override
    public boolean isUserAnswered(int userId, int questionId) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_USER_ID_BY_QUESTION_ID);
            st.setInt(1, userId);
            st.setInt(2, questionId);
            
            set=st.executeQuery();
            
            if(set.next()){            
                return true;
            }
            else{
                return false;
            }
            
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        catch(InterruptedException e){
            throw new DAOException(e);
        }
        finally{

            try {
                st.close();
            } 
            catch (SQLException e) {
                logger.error(ERROR);
            }
            
            conPool.putback(con);
        }
        
    }

    @Override
    public List<User> getUsers() throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        List<User> users=new ArrayList<User>();
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();           
            st=con.prepareStatement(SELECT_USERS);
            
            set=st.executeQuery();
            
            while(set.next()){
                User user=new User();
                user.setId(set.getInt(1));
                user.setName(set.getString(2));
                user.setLogin(set.getString(3));
                user.setRole(set.getString(4));
                users.add(user);
            }
            
            return users;       
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        catch(InterruptedException e){
            throw new DAOException(e);
        }
        finally{

            try {
                st.close();
            } 
            catch (SQLException e) {
                logger.error(ERROR);
            }
            
            conPool.putback(con);
        }
        
    }

    @Override
    public void setAdmin(int id) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();           
            st=con.prepareStatement(SET_ADMIN);  
            
            st.setInt(1, id);
            
            st.executeUpdate();              
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        catch(InterruptedException e){
            throw new DAOException(e);
        }
        finally{

            try {
                st.close();
            } 
            catch (SQLException e) {
                logger.error(ERROR);
            }
            
            conPool.putback(con);
        }
    }

    @Override
    public void deleteAdminRights(int id) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();           
            st=con.prepareStatement(DELETE_ADMIN_RIGHTS);  
            
            st.setInt(1, id);
            
            st.executeUpdate();              
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        catch(InterruptedException e){
            throw new DAOException(e);
        }
        finally{

            try {
                st.close();
            } 
            catch (SQLException e) {
                logger.error(ERROR);
            }
            
            conPool.putback(con);
        }
        
    }
    
}
