package by.tr.op.dao.impl;

import by.tr.op.bean.Question;
import by.tr.op.dao.DAOProvider;
import by.tr.op.dao.QuestionDAO;
import by.tr.op.dao.connection.ConnectionPool;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.listener.ContextListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class QuestionDAOProvider implements QuestionDAO,DAOProvider{
    private final static String SELECT_QUESTIONS_BY_POLL_ID_QUERY="SELECT `id`,`question_text`,`opinion_poll_id`,`author_id` FROM `questions` WHERE `opinion_poll_id`=?";
    private final static String SELECT_QUESTION_BY_ID_QUERY="SELECT `question_text`,`opinion_poll_id`,`author_id` FROM `questions` WHERE `id`=?";
    private final static String ADD_USER_AND_QUESTION_CONNECTION="INSERT INTO `questions_users_connect`(`user_id`,`question_id`) VALUES(?,?)";
    private final static String ADD_QUESTION="INSERT INTO `questions`(`id`,`question_text`,`opinion_poll_id`,`author_id`) VALUES(?,?,?,?)";
    private final static String SELECT_MAX_QUESTION_ID_QUERY="SELECT MAX(`id`) FROM `questions`";
    private final static String DELETE_QUESTION="DELETE FROM `questions` WHERE `id`=?";
    private final static String ERROR="PreparedStatement closing error";
    public static final Logger logger = Logger.getLogger(QuestionDAOProvider.class);
    
    @Override
    public List<Question> getQuestionsByPollId(int pollId) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        List<Question> list=new ArrayList<Question>();
        Question question=null;
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_QUESTIONS_BY_POLL_ID_QUERY);
            st.setInt(1, pollId);
            
            set=st.executeQuery();
            
            while(set.next()){
                question=new Question();
                question.setId(set.getInt(1));
                question.setQuestionText(set.getString(2));
                question.setOpinionPollId(set.getInt(3));
                question.setAuthorId(set.getInt(4));
                list.add(question);
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
    public Question getQuestionById(int id) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        Question question=new Question();
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_QUESTION_BY_ID_QUERY);
            st.setInt(1, id);
            
            set=st.executeQuery();

            if(set.next()){
                question=new Question();
                question.setId(id);
                question.setQuestionText(set.getString(1));
                question.setOpinionPollId(set.getInt(2));
                question.setAuthorId(set.getInt(3));
            }
            else{
                throw new DAOException();
            }
            
            return question;
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
    public void addUserAndQuestionConnection(int userId, int questionId) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(ADD_USER_AND_QUESTION_CONNECTION);
            
            st.setInt(1, userId);
            st.setInt(2, questionId);
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
    public void addQuestion(int id, String questionText, int opinionPollId, int authorId) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(ADD_QUESTION);
            
            st.setInt(1, id);
            st.setString(2, questionText);
            st.setInt(3, opinionPollId);
            st.setInt(4, authorId);
            
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
    public int getQuestionCount() throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_MAX_QUESTION_ID_QUERY);

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
    public void deleteQuestion(int id) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(DELETE_QUESTION);
            
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
