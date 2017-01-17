package by.tr.op.command.impl;

import by.tr.op.bean.Image;
import by.tr.op.command.Command;
import by.tr.op.service.FindImageService;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.factory.ServiceFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetImages implements Command{
    private final static String IMAGES="images";
    private final static String ID="id";
    private final static String REQUEST_ERROR_MESSAGE="errorMessage";
    private final static String ERROR_MESSAGE="local.get_images_error_message";
    private final static String PAGE_REDIRECT="pageRedirect";
    private final static String FIND_IMAGE_SERVICE="FIND_IMAGE_SERVICE";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory factory=ServiceFactory.getInstance();
        FindImageService findImageService=(FindImageService)factory.getService(FIND_IMAGE_SERVICE);
        List<Image> images;
        
        String id=request.getParameter(ID);
        
        if(id!=null){
            request.setAttribute(ID, Integer.valueOf(id));
        }
        
        try{
            images=findImageService.getImages();
            request.setAttribute(IMAGES, images);
        }
        catch(ServiceException e){
            request.setAttribute(REQUEST_ERROR_MESSAGE, ERROR_MESSAGE);
        }
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(request.getParameter(PAGE_REDIRECT));
	requestDispatcher.forward(request, response);
    }
    
}
