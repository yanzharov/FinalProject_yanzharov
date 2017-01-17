package by.tr.op.dao.impl;

import by.tr.op.bean.Option;
import by.tr.op.dao.DAOProvider;
import by.tr.op.dao.OptionDAO;
import by.tr.op.dao.connection.ConnectionPool;
import by.tr.op.dao.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class OptionDAOProvider implements OptionDAO,DAOProvider{
    private final static String ADD_OPTION="INSERT INTO `options`(`id`,`text`,`question_id`) VALUES(?,?,?)";
    private final static String SELECT_MAX_OPTION_ID_QUERY="SELECT MAX(`id`) FROM `options`";
    private final static String SELECT_OPTIONS_BY_QUESTION_ID_QUERY="SELECT `id`,`text`,`question_id` FROM `options` WHERE `question_id`=?";
    private final static String ERROR="PreparedStatement closing error";
    public static final Logger logger = Logger.getLogger(OptionDAOProvider.class);
    
    @Override
    public List<Option> getOptionsByQuestionId(int questionId) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        Option option;
        List<Option> list=new ArrayList<Option>();
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_OPTIONS_BY_QUESTION_ID_QUERY);
            st.setInt(1, questionId);
            
            set=st.executeQuery();
            
            while(set.next()){
                option=new Option();
                option.setId(set.getInt(1));
                option.setText(set.getString(2));
                option.setQuestionId(questionId);
                list.add(option);
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
    public void addOption(int id, String text, int questionId) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(ADD_OPTION);
            
            st.setInt(1, id);
            st.setString(2, text);
            st.setInt(3, questionId);
            
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
    public int getOptionCount() throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(SELECT_MAX_OPTION_ID_QUERY);

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
    
}
