package ops.gateway.util.Mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonMapper {
    private static Logger logger = LoggerFactory.getLogger(CommonMapper.class.getName());

    public static String objectToJsonString(Object objectParsed) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(objectParsed);
            return jsonString;
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getStackTrace());
            return null;
        }
    }
}
