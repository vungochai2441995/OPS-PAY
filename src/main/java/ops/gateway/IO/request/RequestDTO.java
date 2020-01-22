package ops.gateway.IO.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RequestDTO implements Serializable {
    private static final long serialVersionUID = -7708564395285861220L;
    @NotNull(message = "Không được null")
    private String session_id;
}
