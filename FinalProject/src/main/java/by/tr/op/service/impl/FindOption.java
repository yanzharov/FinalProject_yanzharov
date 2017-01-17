package by.tr.op.service.impl;

import by.tr.op.bean.Option;
import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.OptionDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.FindOptionService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;
import java.util.List;

public class FindOption implements FindOptionService,Service{
    private final static String OPTION_DAO="OPTION_DAO";

    @Override
    public List<Option> getOptionsByQuestionId(int questionId) throws ServiceException, WrongIdException {
        
        if (questionId<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        OptionDAO optionDao=(OptionDAO)factory.getDAOProvider(OPTION_DAO);
        List<Option> list;
        
        try{
            list=optionDao.getOptionsByQuestionId(questionId);
            return list;
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
}
