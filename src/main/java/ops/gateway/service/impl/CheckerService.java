package ops.gateway.service.impl;

import ops.gateway.batis.RequestMasterMapper;
import ops.gateway.entities.RequestMaster;
import ops.gateway.model.request.CheckerRequest;
import ops.gateway.model.request.EWalletServiceRequest;
import ops.gateway.repository.MakerRepository;
import ops.gateway.service.ICheckerService;
import ops.gateway.util.Constant;
import ops.gateway.util.Mapper.CheckerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CheckerService implements ICheckerService {
    @Autowired
    MakerRepository makerRepository;

    @Autowired
    RequestMasterMapper requestMasterMapper;

    @Transactional
    @Override
    public ResponseEntity<String> checker(List<CheckerRequest> checkerRequestList) {
        List<String> originalTransIdList = new CopyOnWriteArrayList<>();
        for (CheckerRequest checkerRequest : checkerRequestList) {
            try {
                makerRepository.updateRequestMasterSetStatusForOriginalTransAndUserId(Constant.Status.APPROVED.toString(), checkerRequest.getOriginalTransId(), checkerRequest.getUserId());
            } catch (RuntimeException ex) {
                System.out.println(ex.getStackTrace());
            }
        }
        try {
            List<EWalletServiceRequest> eWalletServiceRequestList = CheckerMapper.CheckerRequestToEWalletRequestMapper(checkerRequestList);
            return connectEWalletService(eWalletServiceRequestList);
        }catch (RuntimeException ex) {
            System.out.println(ex.getStackTrace());
            return null;
        }
    }

    @Override
    public RequestMaster test() {
        RequestMaster requestMaster = requestMasterMapper.selectOne(134);
        requestMaster.setObjectId((long) 1);
        requestMasterMapper.insertRequestMaster(requestMaster);
        return null;
    }

    private ResponseEntity<String> connectEWalletService(List<EWalletServiceRequest> eWalletServiceRequestList){
        final String uri = "http://localhost:8085/e-wallet-service";
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri,eWalletServiceRequestList,String.class);
        return responseEntity;
    }
}
