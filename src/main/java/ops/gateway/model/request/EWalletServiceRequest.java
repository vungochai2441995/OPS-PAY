package ops.gateway.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EWalletServiceRequest {
    private Long objectID;
    private String requestServiceId;
    private Date requestTime;
    private String requestBody;
    private String originalTransId;

}
