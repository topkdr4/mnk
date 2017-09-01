package beans;
/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public class ResultValue {
    
    private final double valueX;
    private final double valueY;
    
    
    public ResultValue(double valueX, double valueY) {
        this.valueX = valueX;
        this.valueY = valueY;
    }
    
    
    public double getValueX() {
        return valueX;
    }
    
    
    public double getValueY() {
        return valueY;
    }
}
