package by.tr.op.dao.impl;

import by.tr.op.bean.Poll;
import by.tr.op.dao.DAOProvider;
import by.tr.op.dao.connection.ConnectionPool;
import by.tr.op.dao.PollsDAO;
import by.tr.op.dao.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class PollsDAOProvider implements PollsDAO,DAOProvider{
    
    private final static String SELECT_POLLS_QUERY="SELECT `id`,`theme`,`author_id`,`state`,`date`,`image` FROM `opinion_polls`";
    private final static String SELECT_POLL_BY_ID_QUERY="SELECT `theme`,`author_id`,`state`,`date`,`image` FROM `opinion_polls` WHERE `id`=?";
    private final static String EDIT_POLL_QUERY="UPDATE `opinion_polls` SET `theme`=?,`state`=?,`image`=? WHERE `id`=?";
    private final static String DELETE_POLL_QUERY="DELETE FROM `opinion_polls` WHERE `id`=?";
    private final static String ADD_POLL_QUERY="INSERT INTO `opinion_polls`(`id`,`theme`,`author_id`,`state`,`date`,`image`) VALUES(?,?,?,?,NOW(),?)";
    private final static String SELECT_MAX_POLL_ID_QUERY="SELECT MAX(`id`) FROM `opinion_polls`";
    private final static String ADD_POLL_AND_USER_CONNECTION="INSERT INTO `users_themes_connect`(`user_id`,`opinion_poll_id`) VALUES(?,?)";
    private final static String GET_POPULAR_POLLS="SELECT `poll`.`id`,`poll`.`theme`,`poll`.`author_id`,`poll`.`state`,`poll`.`date`,`poll`.`image`,`con`.`opinion_poll_id`, COUNT(`con`.`user_id`) AS `count` " +
    "FROM `opinion_polls` AS `poll` INNER JOIN `users_themes_connect` AS `con` ON `poll`.`id`=`con`.`opinion_poll_id` " +
    "GROUP BY `con`.`opinion_poll_id` ORDER BY `count` DESC LIMIT 3;";
    private final static String SELECT_POLL_ID_IF_NOT_EXISTS="SELECT `opinion_poll_id` FROM `users_themes_connect` WHERE `user_id`=? AND `opinion_poll_id`=?";
    private final static String ERROR="PreparedStatement closing error";
    public static final Logger logger = Logger.getLogger(PollsDAOProvider.class);
    
    @Override
    public List<Poll> getPolls() throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        List<Poll> list=new ArrayList<Poll>();
        Poll poll=null;
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_POLLS_QUERY);
            
            set=st.executeQuery();
            
            while(set.next()){
                poll=new Poll();
                poll.setId(set.getInt(1));
                poll.setTheme(set.getString(2));
                poll.setAuthorId(set.getInt(3));
                poll.setState(set.getString(4));
                poll.setDate(set.getDate(5));
                poll.setImage(set.getString(6));
                list.add(poll);
            }
            
            return list;
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
    public void editPoll(int id, String theme, String state, String image) throws DAOException{
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(EDIT_POLL_QUERY);
            
            st.setString(1, theme);
            st.setString(2, state);
            st.setString(3, image);
            st.setInt(4, id);
            
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
    public void deletePoll(int id) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(DELETE_POLL_QUERY);
            
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
    public Poll getPollById(int id) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        Poll poll=new Poll();
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(SELECT_POLL_BY_ID_QUERY);
            
            st.setInt(1, id);
            set=st.executeQuery();
            
            if(set.next()){
                poll.setId(id);
                poll.setTheme(set.getString(1));
                poll.setAuthorId(set.getInt(2));
                poll.setState(set.getString(3));
                poll.setDate(set.getDate(4));
                poll.setImage(set.getString(5));
            }
            else{
                throw new DAOException();
            }
            
            return poll;
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
    public void addPoll(int id, String theme, int authorId, String state, String image) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();    
            st=con.prepareStatement(ADD_POLL_QUERY);

            st.setInt(1, id);
            st.setString(2, theme);
            st.setInt(3, authorId);
            st.setString(4, state);
            st.setString(5, image);
            
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
    public int getPollCount() throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_MAX_POLL_ID_QUERY);

            set=st.executeQuery();
            
            if(set.next()){
                return set.getInt(1);
            }
            else{
                throw new DAOException();
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
    public void addPollAndUserConnect(int userId,int pollId) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(ADD_POLL_AND_USER_CONNECTION);
            
            st.setInt(1,userId);
            st.setInt(2,pollId);
            
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
    public List<Poll> getPopularPolls() throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        Poll poll=null;
        List<Poll> list=new ArrayList<Poll>();
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(GET_POPULAR_POLLS);
            
            set=st.executeQuery();
            
            while(set.next()){
                poll=new Poll();
                poll.setId(set.getInt(1));
                poll.setTheme(set.getString(2));
                poll.setAuthorId(set.getInt(3));
                poll.setState(set.getString(4));
                poll.setDate(set.getDate(5));
                poll.setImage(set.getString(6));
                list.add(poll);
            }
            
            return list;
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
    public boolean isUserAnswered(int userId,int pollId) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_POLL_ID_IF_NOT_EXISTS);
            
            st.setInt(1, userId);
            st.setInt(2, pollId);
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

}
