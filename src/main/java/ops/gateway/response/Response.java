package ops.gateway.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    @JsonProperty("return_message")
    private String message;

    @JsonProperty("return_code")
    private String code;

    @JsonProperty("return_httpStatus")
    private HttpStatus httpStatus;
}
