package ops.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {
    @JsonProperty("code")
    private String code;
    @JsonProperty("message")
    private String message;

    @JsonProperty("detail")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> detail;

    public Meta(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
