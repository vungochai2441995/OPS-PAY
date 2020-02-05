package ops.gateway.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ops.gateway.model.dto.MakerSuccessDataDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EWalletServiceResponse {
//    @JsonProperty("list_success")
    private List<MakerSuccessDataDTO> successList;

//    @JsonProperty("list_fail")
    private List<MakerSuccessDataDTO> failList;

//    @JsonProperty("status")
    private int status;
}
