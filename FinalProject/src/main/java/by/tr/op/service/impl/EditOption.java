package by.tr.op.service.impl;

import by.tr.op.dao.DAOFactory;
import by.tr.op.dao.OptionDAO;
import by.tr.op.dao.exception.DAOException;
import by.tr.op.service.EditOptionService;
import by.tr.op.service.Service;
import by.tr.op.service.exception.EmptyTextException;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.exception.WrongIdException;

public class EditOption implements EditOptionService,Service{
    private final static String OPTION_DAO="OPTION_DAO";

    @Override
    public void addOption(String text, int questionId) throws ServiceException, EmptyTextException, WrongIdException {
        
        if (text == null || "".equals(text)){
            throw new EmptyTextException();
	}
        
        if (questionId<0){
            throw new WrongIdException();
	}
        
        DAOFactory factory=DAOFactory.getInstance();
        OptionDAO optionDao=(OptionDAO)factory.getDAOProvider(OPTION_DAO);
        
        try{
            int count=optionDao.getOptionCount();
            optionDao.addOption(count+1, text, questionId);
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
        
    }
    
}
