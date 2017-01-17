package by.tr.op.dao;

import by.tr.op.bean.Poll;
import by.tr.op.dao.exception.DAOException;
import java.util.List;

public interface PollsDAO {
    
    List<Poll> getPolls() throws DAOException;
    void editPoll(int id,String theme,String state,String image) throws DAOException;
    void deletePoll(int id) throws DAOException;
    Poll getPollById(int id) throws DAOException;
    void addPoll(int id,String theme,int authorId,String state,String image) throws DAOException;
    int getPollCount() throws DAOException;
    void addPollAndUserConnect(int userId,int pollId) throws DAOException;
    List<Poll> getPopularPolls() throws DAOException;
    boolean isUserAnswered(int userId,int pollId) throws DAOException;
    
}
