package by.tr.op.service.exception;

public class WrongIdException extends Exception{
    private static final long serialVersionUID = 1L;

    public WrongIdException(){
	super();
    }
	
    public WrongIdException(String message){
	super(message);
    }	
	
    public WrongIdException(Exception e){
	super(e);
    }
	
    public WrongIdException(String message, Exception e){
	super(message, e);
    }
    
}
