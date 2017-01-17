package by.tr.op.dao;

import by.tr.op.bean.Option;
import by.tr.op.dao.exception.DAOException;
import java.util.List;

public interface OptionDAO {
    
    List<Option> getOptionsByQuestionId(int questionId) throws DAOException;
    void addOption(int id,String text,int questionId) throws DAOException;
    int getOptionCount() throws DAOException;
    
}
