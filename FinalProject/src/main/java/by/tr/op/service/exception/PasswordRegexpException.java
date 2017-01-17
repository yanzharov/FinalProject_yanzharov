package by.tr.op.service.exception;

public class PasswordRegexpException extends Exception {
    private static final long serialVersionUID = 1L;

    public PasswordRegexpException(){
	super();
    }
	
    public PasswordRegexpException(String message){
	super(message);
    }	
	
    public PasswordRegexpException(Exception e){
	super(e);
    }
	
    public PasswordRegexpException(String message, Exception e){
	super(message, e);
    }
}
