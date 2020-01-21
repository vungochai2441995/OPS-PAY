package ops.gateway.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResponseDTO {
    public ResponseDTO(String message, String code) {
        this.responseMeta = new ResponseMeta(message,code);
    }

    public ResponseDTO(String message, String code, String functionName) {
        this.responseMeta = new ResponseMeta(message,code);
        this.responseBody = new ResponseBody(functionName);
    }

    @JsonProperty("meta")
    private ResponseMeta responseMeta;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("body")
    private ResponseBody responseBody;

    @Data
    @AllArgsConstructor
    class ResponseMeta {
        @JsonProperty("message")
        private String message;

        @JsonProperty("code")
        private String code;
    }

    @Data
    @AllArgsConstructor
    class ResponseBody {
        @JsonProperty("function_name")
        private String function_name;
    }
}


