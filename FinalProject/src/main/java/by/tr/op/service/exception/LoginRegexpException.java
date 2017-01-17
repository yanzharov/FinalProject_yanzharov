package by.tr.op.service.exception;

public class LoginRegexpException extends Exception {
    private static final long serialVersionUID = 1L;

    public LoginRegexpException(){
	super();
    }
	
    public LoginRegexpException(String message){
	super(message);
    }	
	
    public LoginRegexpException(Exception e){
	super(e);
    }
	
    public LoginRegexpException(String message, Exception e){
	super(message, e);
    }
}
