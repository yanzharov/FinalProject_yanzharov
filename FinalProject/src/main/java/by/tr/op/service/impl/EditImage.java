package by.tr.op.service.impl;

import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.ImageDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.EditImageService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.ServiceException;

public class EditImage implements EditImageService,Service{
    private final static String IMAGE_DAO="IMAGE_DAO";

    @Override
    public void addImage(String name) throws ServiceException {
        DAOFactory factory=DAOFactory.getInstance();
        ImageDAO imageDao=(ImageDAO)factory.getDAOProvider(IMAGE_DAO);
        
        try{
            int count=imageDao.getImageCount();
            imageDao.addImage(count+1, name);
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
   
    
}
