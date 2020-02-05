package ops.gateway.service.impl;

import ops.gateway.entities.RequestMaster;
import ops.gateway.model.dto.MakerSuccessDataDTO;
import ops.gateway.model.request.MakerRequest;
import ops.gateway.model.response.CommonResponse;
import ops.gateway.model.response.MakerResponse;
import ops.gateway.model.response.common.ResponseConstant;
import ops.gateway.repository.MakerRepository;
import ops.gateway.service.IMakerService;
import ops.gateway.util.Mapper.MakerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class MakerService implements IMakerService {
    @Autowired
    MakerRepository makerRepository;

    @Override
    public CommonResponse createMaker(List<MakerRequest> makerRequestList) {
        String nameOfCurrMethod = new Object() {
        }
                .getClass()
                .getEnclosingMethod()
                .getName();
        String methodName = this.getClass().getSimpleName() + "." + nameOfCurrMethod;
        List<MakerSuccessDataDTO> listSuccessRequest = new CopyOnWriteArrayList<>();
        List<String> listFailRequest = new CopyOnWriteArrayList<>();
        int status;

        if (makerRequestList != null && !makerRequestList.isEmpty()) {
            for (MakerRequest makerRequest : makerRequestList) {
                RequestMaster requestMaster = MakerMapper.GateWayRequestToRequestMasterEntity(makerRequest);
                try {
                    RequestMaster saveResult = makerRepository.save(requestMaster);


                    MakerSuccessDataDTO makerSuccessDataDTO = new MakerSuccessDataDTO(saveResult.getOriginalTransId(),
                            saveResult.getObjectId(), makerRequest.getDesc(),makerRequest.getAmount());
                    listSuccessRequest.add(makerSuccessDataDTO);
                } catch (RuntimeException ex) {
                    listFailRequest.add(makerRequest.getOriginalTransId());
                    continue;
                }
            }
        }
        if (!listSuccessRequest.isEmpty()) {
            if (listFailRequest.isEmpty()) status = 1;
            else status = 2;
        } else status = 3;

        MakerResponse makerResponse = new  MakerResponse(listSuccessRequest, listFailRequest, status);
        return ResponseConstant.responseOK(methodName,makerResponse);
    }

}
