package by.tr.op.dao;

import by.tr.op.bean.AnswerResult;
import by.tr.op.dao.exception.DAOException;
import java.util.List;

public interface AnswerDAO {
    
    void addNewAnswer(int id,String answerText,int userId,int questionId) throws DAOException;
    int getAnswerCount() throws DAOException;
    List<AnswerResult> getAnswersAndResultsByQuestionId(int questionId) throws DAOException;
    
}
