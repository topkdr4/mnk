package util;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
public final class Util {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }


    public static String toJson(Object arg) throws JsonProcessingException {
        return mapper.writeValueAsString(arg);
    }

}
