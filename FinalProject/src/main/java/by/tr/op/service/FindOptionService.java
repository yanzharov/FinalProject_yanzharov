package by.tr.op.service;

import by.tr.op.bean.Option;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import java.util.List;

public interface FindOptionService {
    
    List<Option> getOptionsByQuestionId(int questionId) throws ServiceException,WrongIdException;
    
}
