package by.tr.op.service.exception;

public class EmptyTextException extends Exception{
    private static final long serialVersionUID = 1L;

    public EmptyTextException(){
	super();
    }
	
    public EmptyTextException(String message){
	super(message);
    }	
	
    public EmptyTextException(Exception e){
	super(e);
    }
	
    public EmptyTextException(String message, Exception e){
	super(message, e);
    }
}
