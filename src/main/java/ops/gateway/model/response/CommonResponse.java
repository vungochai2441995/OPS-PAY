package ops.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ops.gateway.model.response.common.Body;
import ops.gateway.model.response.common.Meta;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("meta")
    private Meta responseMeta;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("data")
    private Body responseBody;

    public CommonResponse(String code, String message, List<String> detail) {
        if (code != null && message != null)
            this.responseMeta = new Meta(code, message, detail);
    }

    public CommonResponse(String code, String message, MakerResponse makerResponse, String functionName) {
        this.responseMeta = new Meta(code, message);
        this.responseBody = new Body(functionName, functionName,makerResponse);
    }

    public CommonResponse(String code, String message, String functionName) {
        this.responseMeta = new Meta(code, message);
        this.responseBody = new Body(functionName, functionName);
    }
}


