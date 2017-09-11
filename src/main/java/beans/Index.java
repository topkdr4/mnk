package beans;
/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public class Index {
    
    private final int uid;
    private final String name;
    
    
    public Index(int uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public Index(int uid) {
        this.uid = uid;
        this.name = "";
    }
    
    
    public int getUid() {
        return uid;
    }
    
    
    public String getName() {
        return name;
    }
}
