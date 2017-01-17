package by.tr.op.service.exception;

public class WrongStateException extends Exception {
    private static final long serialVersionUID = 1L;

    public WrongStateException(){
	super();
    }
	
    public WrongStateException(String message){
	super(message);
    }	
	
    public WrongStateException(Exception e){
	super(e);
    }
	
    public WrongStateException(String message, Exception e){
	super(message, e);
    }
}
