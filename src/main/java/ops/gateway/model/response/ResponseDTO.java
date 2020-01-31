package ops.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("meta")
    private Meta responseMeta;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("data")
    private Body responseBody;

    public ResponseDTO(String code, String message) {
        if (code != null && message != null)
            this.responseMeta = new Meta(code,message);
    }

    public ResponseDTO(String code, String message, List<String> detail) {
        if (code != null && message != null)
            this.responseMeta = new Meta(code,message,detail);
    }

    public ResponseDTO(String code, String message, List<String> detail, String functionName) {
        this.responseMeta = new Meta(code,message,detail);
        this.responseBody = new Body(functionName,functionName);
    }
}


