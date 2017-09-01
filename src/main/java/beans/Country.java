package beans;
import java.nio.file.Path;
import java.nio.file.Paths;





/**
 * Ветошкин А.В. РИС-16бзу  
 * */

public class Country {
    
    private final int id;
    private final String name;
    private final Path pathToFile;
    
    
    public Country(String name, int id, String pathToFile) {
        this.name = name;
        this.id = id;
        this.pathToFile = Paths.get(pathToFile);
    }
    
    
}
