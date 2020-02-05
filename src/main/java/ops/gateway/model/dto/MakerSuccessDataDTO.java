package ops.gateway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakerSuccessDataDTO {
    private String originalTransId;
    private Long objectId;
    private String desc;
    private double amount;
}
