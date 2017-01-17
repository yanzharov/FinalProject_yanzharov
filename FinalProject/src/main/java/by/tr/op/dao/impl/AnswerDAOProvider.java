package by.tr.op.dao.impl;

import by.tr.op.bean.AnswerResult;
import by.tr.op.dao.AnswerDAO;
import by.tr.op.dao.DAOProvider;
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

public class AnswerDAOProvider implements AnswerDAO,DAOProvider{
    private final static String ADD_ANSWER_QUERY="INSERT INTO `answers`(`id`,`answer_text`,`user_id`,`question_id`) VALUES(?,?,?,?)";
    private final static String SELECT_MAX_ANSWER_ID_QUERY="SELECT MAX(`id`) FROM `answers`";
    private final static String SELECT_ANSWERS_AND_RESULTS_BY_QUESTION_ID="SELECT `answer_text`,COUNT(`id`) FROM `answers` WHERE `question_id`=? GROUP BY `answer_text`";
    private final static String ERROR="PreparedStatement closing error";
    public static final Logger logger = Logger.getLogger(AnswerDAOProvider.class);
    
    @Override
    public void addNewAnswer(int id, String answerText, int userId, int questionId) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(ADD_ANSWER_QUERY);
            
            st.setInt(1, id);
            st.setString(2, answerText);
            st.setInt(3, userId);
            st.setInt(4, questionId);
            
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
    public int getAnswerCount() throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        int count=-1;
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_MAX_ANSWER_ID_QUERY);

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
    public List<AnswerResult> getAnswersAndResultsByQuestionId(int questionId) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        AnswerResult answerResult=null;
        List<AnswerResult> list=new ArrayList<AnswerResult>();
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_ANSWERS_AND_RESULTS_BY_QUESTION_ID);
            st.setInt(1, questionId);
            
            set=st.executeQuery();
            
            while(set.next()){            
                answerResult=new AnswerResult();
                answerResult.setAnswerText(set.getString(1));
                answerResult.setCount(set.getInt(2));
                list.add(answerResult);
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
}
