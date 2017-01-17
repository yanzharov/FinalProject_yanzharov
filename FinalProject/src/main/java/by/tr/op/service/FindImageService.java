package by.tr.op.service;

import by.tr.op.bean.Image;
import by.tr.op.service.exception.ServiceException;
import java.util.List;

public interface FindImageService {
    
    List<Image> getImages() throws ServiceException;
    
}
