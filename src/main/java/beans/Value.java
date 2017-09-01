package beans;
/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public class Value implements Comparable<Value> {
    
    private final double valueY;
    private final double valueX;
    private int uid;
    
    
    public Value(double valueX, double valueY) {
        this.valueX = valueX;
        this.valueY = valueY;
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
    
    
    @Override
    public int compareTo(Value o) {
        return Double.compare(valueX, o.valueX);
    }
}
