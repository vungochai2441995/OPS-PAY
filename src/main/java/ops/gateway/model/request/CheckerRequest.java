package ops.gateway.model.request;

import lombok.Data;

@Data
public class CheckerRequest {
    private Long ObjectId;
    private String userName;
    private String desc;
    private String originalTransId;
    private Long userId;
    private double amount;
}
