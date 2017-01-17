package by.tr.op.dao.impl;

import by.tr.op.bean.Image;
import by.tr.op.dao.DAOProvider;
import by.tr.op.dao.ImageDAO;
import by.tr.op.dao.connection.ConnectionPool;
import by.tr.op.dao.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class ImageDAOProvider implements ImageDAO,DAOProvider{
    private final static String GET_IMAGES="SELECT `id`,`name` FROM `images`";
    private final static String ADD_IMAGE="INSERT INTO `images`(`id`,`name`) VALUES(?,?)";
    private final static String GET_IMAGE_COUNT="SELECT MAX(`id`) FROM `images`";
    private final static String ERROR="PreparedStatement closing error";
    public static final Logger logger = Logger.getLogger(ImageDAOProvider.class);
    
    @Override
    public List<Image> getImages() throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        List<Image> list=new ArrayList<Image>();
        PreparedStatement st=null;
        ResultSet set=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(GET_IMAGES);
            
            set=st.executeQuery();
            
            while(set.next()){            
                Image image=new Image();
                image.setId(set.getInt(1));
                image.setName(set.getString(2));
                list.add(image);
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
    public void addImage(int id, String name) throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(ADD_IMAGE);
            
            st.setInt(1, id);
            st.setString(2, name);
            
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
    public int getImageCount() throws DAOException {
        ConnectionPool conPool=ConnectionPool.getInstance();
        Connection con=null;
        PreparedStatement st=null;
        ResultSet set=null;
        int count=-1;
        
        try{
            con=conPool.retrieve();
            st=con.prepareStatement(GET_IMAGE_COUNT);
            
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
    
}
