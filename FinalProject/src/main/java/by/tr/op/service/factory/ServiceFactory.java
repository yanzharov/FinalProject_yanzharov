package by.tr.op.service.factory;

import by.tr.op.service.Service;
import by.tr.op.service.impl.Authorization;
import by.tr.op.service.impl.EditAnswer;
import by.tr.op.service.impl.EditImage;
import by.tr.op.service.impl.EditOption;
import by.tr.op.service.impl.EditPoll;
import by.tr.op.service.impl.EditQuestion;
import by.tr.op.service.impl.EditUser;
import by.tr.op.service.impl.FindAnswer;
import by.tr.op.service.impl.FindImage;
import by.tr.op.service.impl.FindOption;
import by.tr.op.service.impl.FindPoll;
import by.tr.op.service.impl.FindQuestion;
import by.tr.op.service.impl.FindUser;
import by.tr.op.service.impl.InitializeConnection;
import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private static final ServiceFactory INSTANCE = new ServiceFactory();
	
    private final static String AUTHORIZATION_SERVICE="AUTHORIZATION_SERVICE";
    private final static String EDIT_USER_SERVICE="EDIT_USER_SERVICE";
    private final static String FIND_POLL_SERVICE="FIND_POLL_SERVICE";
    private final static String EDIT_POLL_SERVICE="EDIT_POLL_SERVICE";
    private final static String FIND_QUESTION_SERVICE="FIND_QUESTION_SERVICE";
    private final static String FIND_OPTION_SERVICE="FIND_OPTION_SERVICE";
    private final static String EDIT_ANSWER_SERVICE="EDIT_ANSWER_SERVICE";
    private final static String FIND_ANSWER_SERVICE="FIND_ANSWER_SERVICE";
    private final static String FIND_USER_SERVICE="FIND_USER_SERVICE";
    private final static String INITIALIZE_CONNECTION_SERVICE="INITIALIZE_CONNECTION_SERVICE";
    private final static String EDIT_QUESTION_SERVICE="EDIT_QUESTION_SERVICE";
    private final static String EDIT_OPTION_SERVICE="EDIT_OPTION_SERVICE";
    private final static String FIND_IMAGE_SERVICE="FIND_IMAGE_SERVICE";
    private final static String EDIT_IMAGE_SERVICE="EDIT_IMAGE_SERVICE";
    
    private Map<String, Service> services = new HashMap<String, Service>();
    
    private ServiceFactory(){
        services.put(AUTHORIZATION_SERVICE, new Authorization());
        services.put(EDIT_USER_SERVICE, new EditUser());
        services.put(FIND_POLL_SERVICE, new FindPoll());
        services.put(EDIT_POLL_SERVICE, new EditPoll());
        services.put(FIND_QUESTION_SERVICE, new FindQuestion());
        services.put(FIND_OPTION_SERVICE, new FindOption());
        services.put(EDIT_ANSWER_SERVICE, new EditAnswer());
        services.put(FIND_ANSWER_SERVICE, new FindAnswer());
        services.put(FIND_USER_SERVICE, new FindUser());
        services.put(INITIALIZE_CONNECTION_SERVICE, new InitializeConnection());
        services.put(EDIT_QUESTION_SERVICE, new EditQuestion());
        services.put(EDIT_OPTION_SERVICE, new EditOption());
        services.put(FIND_IMAGE_SERVICE, new FindImage());
        services.put(EDIT_IMAGE_SERVICE, new EditImage());
    }
	
    public Service getService(String serviceName){
	Service service;
	service = services.get(serviceName);
	return service;
    }

    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
    
}
