package by.tr.op.dao;

import by.tr.op.dao.impl.AnswerDAOProvider;
import by.tr.op.dao.impl.ConInit;
import by.tr.op.dao.impl.ImageDAOProvider;
import by.tr.op.dao.impl.OptionDAOProvider;
import by.tr.op.dao.impl.PollsDAOProvider;
import by.tr.op.dao.impl.QuestionDAOProvider;
import by.tr.op.dao.impl.UserDAOProvider;
import java.util.HashMap;
import java.util.Map;

public class DAOFactory {
    private static final DAOFactory INSTANCE = new DAOFactory();
		
    private final static String POLLS_DAO="POLLS_DAO";
    private final static String USER_DAO="USER_DAO";
    private final static String QUESTION_DAO="QUESTION_DAO";
    private final static String ANSWER_DAO="ANSWER_DAO";
    private final static String CON_INIT="CON_INIT";
    private final static String OPTION_DAO="OPTION_DAO";
    private final static String IMAGE_DAO="IMAGE_DAO";
    private Map<String, DAOProvider> daoProviders = new HashMap<String, DAOProvider>();

    private DAOFactory(){
        daoProviders.put(POLLS_DAO, new PollsDAOProvider());
        daoProviders.put(USER_DAO, new UserDAOProvider());
        daoProviders.put(QUESTION_DAO, new QuestionDAOProvider());
        daoProviders.put(ANSWER_DAO, new AnswerDAOProvider());
        daoProviders.put(CON_INIT, new ConInit());
        daoProviders.put(OPTION_DAO, new OptionDAOProvider());
        daoProviders.put(IMAGE_DAO, new ImageDAOProvider());
    }
	
    public DAOProvider getDAOProvider(String daoName){
	DAOProvider daoProvider;
	daoProvider = daoProviders.get(daoName);
	return daoProvider;
    }
    
    public static DAOFactory getInstance(){
	return INSTANCE;
    }
	
}