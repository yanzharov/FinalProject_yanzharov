package by.tr.op.dao.impl;

import by.tr.op.dao.ConInitDAO;
import by.tr.op.dao.DAOProvider;
import by.tr.op.dao.connection.ConnectionPool;
import by.tr.op.dao.exception.DAOException;
import java.io.IOException;
import java.sql.SQLException;

public class ConInit implements ConInitDAO,DAOProvider{

    @Override
    public void init() throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        
	try {
	    pool.init();
	} 
        catch(NullPointerException e){
            throw new DAOException(e);
        }
        catch(ClassNotFoundException e){
            throw new DAOException(e);
        }
        catch (SQLException e) {
	    throw new DAOException(e);
	} 
        catch (IOException e) {
            throw new DAOException(e);
        }
        
    }

    @Override
    public void destroy() throws DAOException{
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try {
	    pool.close();
	} 
        catch(NullPointerException e){
            throw new DAOException(e);
        }
        catch (SQLException e) {
	    throw new DAOException(e);
	}
        
    }
    
}
