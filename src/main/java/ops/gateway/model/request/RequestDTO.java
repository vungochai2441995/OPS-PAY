package ops.gateway.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ops.gateway.util.Constant;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(content = JsonInclude.Include.NON_NULL)
public class RequestDTO implements Serializable {
    private static final long serialVersionUID = -7708564395285861220L;
    @NotNull(message = "[session_id] "+ Constant.MessageApi.VALIDATE_NULL)
    @JsonProperty("session_id")
    private Long session_id;

    @NotNull(message = "[file_name] "+ Constant.MessageApi.VALIDATE_NULL)
    @Length(max = 20,message = "[file_name] "+ Constant.MessageApi.VALIDATE_MAX_LENGTH_20)
    @Pattern(regexp = "^[A-Za-z0-9]*$",message = "[file_name] " + Constant.MessageApi.VALIDATE_SPECIAL_CHARACTER)
    @JsonProperty("file_name")
    private String file_name;

}
