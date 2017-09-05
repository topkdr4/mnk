package core.result;
/**
 * Ветошкин А.В. РИС-16бзу
 * */
public class SimpleAnswer {

    public static final SimpleAnswer EMPTY = new SimpleAnswer("{}");
    private Object result;
    private int errorCode;
    private String errorText;


    public SimpleAnswer() {

    }


    public SimpleAnswer(Object result) {
        this.result = result;
    }


    public void setResult(Object result) {
        this.result = result;
    }


    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

}
