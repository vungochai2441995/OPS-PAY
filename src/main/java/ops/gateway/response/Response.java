package ops.gateway.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message;
    private HttpStatus status;
    private String body;
}
