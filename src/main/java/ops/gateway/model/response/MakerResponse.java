package ops.gateway.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ops.gateway.model.dto.MakerSuccessDataDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MakerResponse {
    @JsonProperty("list_success")
    private List<MakerSuccessDataDTO> successList;

    @JsonProperty("list_fail")
    private List<String> listFail;

    @JsonProperty("status")
    private int status;

}