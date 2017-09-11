package beans;
/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public class Value {
    
    private final double valueY;
    private final double valueX;
    private int uid;
    
    
    public Value(double valueX, double valueY) {
        this.valueX = valueX;
        this.valueY = valueY;
    }
    
    public Value (int uid) {
        this.uid = uid;
        this.valueX = 0d;
        this.valueY = 0d;
    }
    
    
    public double getValueY() {
        return valueY;
    }
    
    
    public double getValueX() {
        return valueX;
    }
    
    
    public int getUid() {
        return uid;
    }
    
    
    public void setUid(int uid) {
        this.uid = uid;
    }
    
}
