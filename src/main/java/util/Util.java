package util;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
public class Util {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String FILETYPE_CLASS = "class";

    static {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
    
    
    private Util() {
        
    }


    public static String toJson(Object arg) throws JsonProcessingException {
        return mapper.writeValueAsString(arg);
    }
    
    
    public static Set<Class<?>> getClasses(Path start, Predicate<Class<?>> predicate) throws IOException {
        Stream<Path> paths = Files.find(start,
                Integer.MAX_VALUE,
                (path, att) -> {
                    return path.toString().endsWith(FILETYPE_CLASS) && att.isRegularFile();
                }
        );
        
        final int START_PATH_STRING_LENGTH = start.toString().length();
        
        Set<String> res = paths.map(path -> {
            String currentPath = path.toString();
            return currentPath.substring(START_PATH_STRING_LENGTH + 1);
        }).map(Util::className).collect(Collectors.toSet());
        
        return res.stream().map(name -> {
                try {
                    return Class.forName(name, false, Util.class.getClassLoader());
                } catch (ClassNotFoundException e) {
                    return null;
                }
            }).filter(predicate).collect(Collectors.toSet());
    }
    
    
    private static String className(String source) {
        int length = source.length() - FILETYPE_CLASS.length() - 1;
        char[] result = new char[length];
        
        for (int index = 0; index < length; index++) {
            char code = source.charAt(index);
            
            if (code == '\\' || code == '/')
                code = '.';
            
            result[index] = code;
        }
        return new String(result);
    }
    
}
