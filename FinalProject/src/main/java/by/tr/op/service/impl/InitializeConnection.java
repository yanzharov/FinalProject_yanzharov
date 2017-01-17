package by.tr.op.service.impl;

import by.tr.op.dao.ConInitDAO;
import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.InitializeConnectionService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.ServiceException;

public class InitializeConnection implements InitializeConnectionService,Service{
    private final static String CON_INIT="CON_INIT";
    
    @Override
    public void init() throws ServiceException {
        DAOFactory factory=DAOFactory.getInstance();
        ConInitDAO conInitDAO=(ConInitDAO)factory.getDAOProvider(CON_INIT);
        
        try{
            conInitDAO.init();
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }

    @Override
    public void destroy() throws ServiceException{
        DAOFactory factory=DAOFactory.getInstance();
        ConInitDAO conInitDAO=(ConInitDAO)factory.getDAOProvider(CON_INIT);
        
        try{
            conInitDAO.destroy();
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
}
