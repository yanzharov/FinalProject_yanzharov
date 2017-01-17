package by.tr.op.dao;

import by.tr.op.bean.Image;
import by.tr.op.dao.exception.DAOException;
import java.util.List;

public interface ImageDAO {
    
    List<Image> getImages() throws DAOException;
    void addImage(int id,String name) throws DAOException;
    int getImageCount() throws DAOException;
    
}
