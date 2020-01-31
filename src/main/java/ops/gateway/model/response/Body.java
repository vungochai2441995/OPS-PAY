package ops.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Body {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("function_name")
    private String function_name;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("function_id")
    private String function_id;
}
