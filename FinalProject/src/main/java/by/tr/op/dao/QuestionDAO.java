package by.tr.op.dao;

import by.tr.op.bean.Question;
import by.tr.op.dao.exception.DAOException;
import java.util.List;

public interface QuestionDAO {
    
    List<Question> getQuestionsByPollId(int pollId) throws DAOException;
    Question getQuestionById(int id) throws DAOException;
    void addUserAndQuestionConnection(int userId,int questionId) throws DAOException;
    void addQuestion(int id,String questionText,int opinionPollId,int authorId) throws DAOException;
    int getQuestionCount() throws DAOException;
    void deleteQuestion(int id) throws DAOException;
    
}
