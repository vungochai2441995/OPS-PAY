package ops.gateway.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ops.gateway.util.Constant;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakerRequest {
    private String userName;

    private Integer transactionTempId;

    @NotNull(message = "[user_id] "+ Constant.MessageApi.VALIDATE_NULL)
    private Integer userId;

    private String desc;

    private Integer amount;

    private String sessionId;

    private Date createdDate = new Date();

    @NotNull(message = "[transaction_type] "+ Constant.MessageApi.VALIDATE_NULL)
    private String transactionType;

    private int isError;

    private String errorDesc;

    private String originalTransId;
}
