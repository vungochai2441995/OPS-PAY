package ops.gateway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakerSuccessDataDTO {
    private String originalTransId;
    private Long objectId;
    private String desc;
    private Integer amount;
}
