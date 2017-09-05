package exceptions;
/**
 * Ветошкин А.В. РИС-16бзу
 * */
public class SystemError extends ServiceException {


    private static final long serialVersionUID = -4534499186001808738L;


    public SystemError() {
        super();
    }


    public SystemError(int errorCode, String message) {
        super(errorCode, message);
    }


    public SystemError(String message) {
        super(message);
    }


    @Override public String toString() {
        return super.toString();
    }
}
