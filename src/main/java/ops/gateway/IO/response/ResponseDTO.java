package ops.gateway.IO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResponseDTO {
    public ResponseDTO(String message, String code) {
        this.responseMeta = new Meta(message,code);
    }

    public ResponseDTO(String message, String code, String functionName) {
        this.responseMeta = new Meta(message,code);
        this.responseBody = new Body(functionName);
    }

    @JsonProperty("meta")
    private Meta responseMeta;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("body")
    private  Body responseBody;

    @Data
    @AllArgsConstructor
    class Meta {
        @JsonProperty("message")
        private String message;

        @JsonProperty("code")
        private String code;
    }

    @Data
    @AllArgsConstructor
    class Body {
        @JsonProperty("function_name")
        private String function_name;
    }
}


