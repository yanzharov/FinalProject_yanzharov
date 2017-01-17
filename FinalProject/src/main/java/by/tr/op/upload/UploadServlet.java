package by.tr.op.upload;

import by.tr.op.service.EditImageService;
import by.tr.op.service.exception.ServiceException;
import by.tr.op.service.factory.ServiceFactory;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
    private final static String REQUEST_ERROR_MESSAGE="errorMessage";
    private final static String ERROR_MESSAGE="local.add_image_error_message";
    private final static String PAGE_REDIRECT="index.jsp";
    private final static String EDIT_IMAGE_SERVICE="EDIT_IMAGE_SERVICE";
    private final static String TEMP_DIR="javax.servlet.context.tempdir";
    private final static String UPLOAD_SERVLET="UploadServlet";
    private final static String IMAGES="/images/";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	
        if (!isMultipart) {
	    request.setAttribute(REQUEST_ERROR_MESSAGE, ERROR_MESSAGE);
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_REDIRECT);
	    requestDispatcher.forward(request, response);
	}
 
	DiskFileItemFactory factory = new DiskFileItemFactory();
 
	factory.setSizeThreshold(1024*1024);
		
	File tempDir = (File)getServletContext().getAttribute(TEMP_DIR);
	factory.setRepository(tempDir);

	ServletFileUpload upload = new ServletFileUpload(factory);
		
	upload.setSizeMax(1024 * 1024 * 10);
 
	try {
	    List items = upload.parseRequest(request);
	    Iterator iter = items.iterator();
			
	    while (iter.hasNext()) {
	        FileItem item = (FileItem) iter.next();
 
	        if (!item.isFormField()) {		    	
		    processUploadedFile(item,request);
		} 
                
	    }			
	} 
        catch (Exception e) {
	    request.setAttribute(REQUEST_ERROR_MESSAGE, ERROR_MESSAGE);
	}
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PAGE_REDIRECT);
	requestDispatcher.forward(request, response);
        
    }

    private void processUploadedFile(FileItem item,HttpServletRequest request) throws Exception {
	File uploadetFile = null;
        ServiceFactory factory=ServiceFactory.getInstance();
        EditImageService editImageService=(EditImageService)factory.getService(EDIT_IMAGE_SERVICE);      

        try{
            editImageService.addImage(item.getName());
        }
        catch(ServiceException e){
            request.setAttribute(REQUEST_ERROR_MESSAGE, ERROR_MESSAGE);
        }
        
	do{
	    String path = getServletContext().getRealPath(IMAGES + item.getName());					
	    uploadetFile = new File(path);		
	}
        while(uploadetFile.exists());
		
	uploadetFile.createNewFile();
	item.write(uploadetFile);
    }
    
    @Override
    public String getServletInfo() {
        return UPLOAD_SERVLET;
    }

}
