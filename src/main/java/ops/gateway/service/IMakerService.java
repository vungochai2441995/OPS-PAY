package ops.gateway.service;

import ops.gateway.model.request.MakerRequest;
import ops.gateway.model.response.CommonResponse;
import ops.gateway.model.response.MakerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMakerService {
    CommonResponse createMaker(List<MakerRequest> makerRequestList);
}
