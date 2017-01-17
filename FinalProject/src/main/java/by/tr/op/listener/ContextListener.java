package by.tr.op.listener;

import by.tr.op.service.InitializeConnectionService;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.factory.ServiceFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

public class ContextListener implements ServletContextListener{
    private final static String INITIALIZE_CONNECTION_SERVICE="INITIALIZE_CONNECTION_SERVICE";
    private final static String INITIALIZATION_ERROR="INITIALIZE_CONNECTION_SERVICE";
    private final static String DESTROYING_ERROR="INITIALIZE_CONNECTION_SERVICE";
    public static final Logger logger = Logger.getLogger(ContextListener.class);
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServiceFactory factory=ServiceFactory.getInstance();
        InitializeConnectionService initializeConnectionService=(InitializeConnectionService)factory.getService(INITIALIZE_CONNECTION_SERVICE);
        
        try{
            initializeConnectionService.init(); 
        }
        catch(ServiceException e){
            logger.error(INITIALIZATION_ERROR);
            throw new RuntimeException();
        }
       
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServiceFactory factory=ServiceFactory.getInstance();
        InitializeConnectionService initializeConnectionService=(InitializeConnectionService)factory.getService(INITIALIZE_CONNECTION_SERVICE);
        
        try{
            initializeConnectionService.destroy(); 
        }
        catch(ServiceException e){
            logger.error(DESTROYING_ERROR);
            throw new RuntimeException();
        } 
        
    }
    
}
