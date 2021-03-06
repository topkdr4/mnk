package exceptions;
/**
 * Ветошкин А.В. РИС-16бзу
 * */
public class ServiceException extends Exception {


    private static final long serialVersionUID = -5583270742114410595L;
    private final int errorCode;
    private final String errorText;


    public ServiceException() {
        super();
        errorCode = 0;
        errorText = null;
    }


    public ServiceException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.errorText = message;
    }



    public ServiceException(String message) {
        super(message);
        this.errorCode = 0;
        this.errorText = message;
    }
    
    
    public ServiceException(Exception e) {
        super(e);
        this.errorText = e.getMessage();
        this.errorCode = -1;
    }
    
    
    public int getErrorCode() {
        return errorCode;
    }


    public String getErrorText() {
        return errorText;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
