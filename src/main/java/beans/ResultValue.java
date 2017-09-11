package beans;
/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public class ResultValue {
    
    private final double valueX;
    private final double valueY;
    private final int uid;
    
    
    public ResultValue(double valueX, double valueY, int uid) {
        this.valueX = valueX;
        this.valueY = valueY;
        this.uid = uid;
    }
    
    
    public double getValueX() {
        return valueX;
    }
    
    
    public double getValueY() {
        return valueY;
    }
    
    
    public int getUid() {
        return uid;
    }
}
