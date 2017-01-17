package by.tr.op.dao;

import by.tr.op.dao.exception.DAOException;

public interface ConInitDAO {
    
    public void init() throws DAOException;
    public void destroy() throws DAOException;
    
}
