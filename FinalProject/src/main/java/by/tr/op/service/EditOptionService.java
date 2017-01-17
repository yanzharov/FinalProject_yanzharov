
package by.tr.op.service;

import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;

public interface EditOptionService {
    
    void addOption(String text, int questionId) throws ServiceException,EmptyTextException,WrongIdException;
    
}
