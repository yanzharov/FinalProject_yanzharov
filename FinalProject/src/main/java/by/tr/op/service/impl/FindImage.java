package by.tr.op.service.impl;

import by.tr.op.bean.Image;
import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.ImageDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.FindImageService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.ServiceException;
import java.util.List;

public class FindImage implements FindImageService,Service{
    private final static String IMAGE_DAO="IMAGE_DAO";
    
    @Override
    public List<Image> getImages() throws ServiceException {
        DAOFactory factory=DAOFactory.getInstance();
        ImageDAO imageDao=(ImageDAO)factory.getDAOProvider(IMAGE_DAO);
        List<Image> list;
        
        try{
            list=imageDao.getImages();
            return list;
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
}
