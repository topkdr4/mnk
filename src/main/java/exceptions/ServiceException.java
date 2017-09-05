package exceptions;
/**
 * Ветошкин А.В. РИС-16бзу
 * */
public class ServiceException extends Throwable {


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
