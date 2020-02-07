package ops.gateway.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
public class EWalletDataDTO {
    private Date responseTime;
    private String originalTransId;
    private String responseTransId;
    private String comment;
    private Long objectId;
}
