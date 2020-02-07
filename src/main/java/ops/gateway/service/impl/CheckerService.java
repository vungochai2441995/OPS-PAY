package ops.gateway.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ops.gateway.batis.RequestMasterMapper;
import ops.gateway.entities.RequestMaster;
import ops.gateway.filter.CorsFilter;
import ops.gateway.model.dto.EWalletDataDTO;
import ops.gateway.model.request.CheckerRequest;
import ops.gateway.model.request.EWalletServiceRequest;
import ops.gateway.repository.CheckerRepository;
import ops.gateway.service.ICheckerService;
import ops.gateway.util.Constant;
import ops.gateway.util.Mapper.CheckerMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CheckerService implements ICheckerService {
    private Logger logger = LoggerFactory.getLogger(CorsFilter.class);
    private List<RequestMaster> resultAfterChangeStatusConvertList = new CopyOnWriteArrayList<>();
    @Autowired
    CheckerRepository checkerRepository;

    @Autowired
    RequestMasterMapper requestMasterMapper;

    @Transactional
    @Override
    public ResponseEntity<String> checker(List<CheckerRequest> checkerRequestList) {
        resultAfterChangeStatusConvertList.clear();
//        for (CheckerRequest checkerRequest : checkerRequestList) {
//            try {
//                checkerRepository.updateRequestMasterSetStatusForOriginalTransAndUserId(Constant.Status.APPROVED.toString(),checkerRequest.getUserId(),checkerRequest.getUserName(), new Date(), checkerRequest.getObjectId());
//            } catch (RuntimeException ex) {
//                logger.info(ex.getMessage());
//            }
//        }

        List<RequestMaster> requestMasterListBeforeCallEWallet = CheckerMapper.checkerRequestListToRequestMasterList(checkerRequestList);
        try {
            Iterable<RequestMaster> resultAfterChangeStatus = changeStatusToAPPROVED(requestMasterListBeforeCallEWallet);
            resultAfterChangeStatusConvertList = (List<RequestMaster>) resultAfterChangeStatus;
            List<EWalletServiceRequest> eWalletServiceRequestList = CheckerMapper.CheckerRequestToEWalletRequestMapper(checkerRequestList);
            ResponseEntity<String> resultConnectToEWallet =  connectEWalletService(eWalletServiceRequestList);
            String resultConnectToEWalletBody = resultConnectToEWallet.getBody();
            getListRequestMasterFromEWalletData(resultConnectToEWalletBody);
            checkerRepository.saveAll(resultAfterChangeStatusConvertList);
            return resultConnectToEWallet;
        }catch (RuntimeException | IOException ex) {
            logger.info(ex.getMessage());
            return null;
        }
    }

    @Override
    public RequestMaster test() {
        RequestMaster requestMaster = requestMasterMapper.selectOne(6);
        requestMaster.setObjectId((long) 6);
        int result = requestMasterMapper.insertRequestMaster(requestMaster);

//        RequestMaster requestMaster = new RequestMaster();
//        requestMaster.setObjectId((long) 6);
//        requestMaster.setMakerRequestId("5");
//        requestMaster.setComment("fuck");
//        RequestMaster result = checkerRepository.saveAndReturn(requestMaster);

        return null;
    }

    private ResponseEntity<String> connectEWalletService(List<EWalletServiceRequest> eWalletServiceRequestList){
        final String uri = "http://localhost:8085/e-wallet-service";
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        return restTemplate.postForEntity(uri,eWalletServiceRequestList,String.class);
    }

    private void getListRequestMasterFromEWalletData(String resultConnectToEWalletBody) throws IOException {

        JSONObject eWalletDataObject = new JSONObject(resultConnectToEWalletBody);

        // 1: All data is success
        // 2: Data is both success and fail
        // 3: All data is fail
        Number statusData = eWalletDataObject.getJSONObject("data").getJSONObject("eWallet_data").getInt("status");
        if (statusData.equals(1)) {
            getListRequestMasterWhenAllDataSuccess(eWalletDataObject);
        }else if (statusData.equals(2)) {
            getListRequestMasterWhenAllDataSuccess(eWalletDataObject);
            getListRequestMasterWhenAllDataFail(eWalletDataObject);
        }else if (statusData.equals(3)) {
             getListRequestMasterWhenAllDataFail(eWalletDataObject);
        }
    }

    private void getListRequestMasterWhenAllDataSuccess(JSONObject eWalletDataObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray eWalletData = eWalletDataObject.getJSONObject("data").getJSONObject("eWallet_data").getJSONArray("successList");
        for (int i = 0; i<eWalletData.length(); i++) {
            JSONObject successDataElement = (JSONObject) eWalletData.get(i);
            EWalletDataDTO eWalletDataDTO = objectMapper.readValue(successDataElement.toString(),EWalletDataDTO.class);
            int index = findRequestMasterByObjectId(eWalletDataDTO.getObjectId(),resultAfterChangeStatusConvertList);
            resultAfterChangeStatusConvertList.set(index,CheckerMapper.eWalletDtoToRequestMaster(eWalletDataDTO,resultAfterChangeStatusConvertList.get(index),
                    Constant.MessageApi.SUCCESS,Constant.Result.SUCCESS.toString()));
        }
    }

    private int findRequestMasterByObjectId(long objectID, List<RequestMaster> requestMasterList) {
        for (int i=0; i<requestMasterList.size(); i++) {
            if (requestMasterList.get(i).getObjectId() == objectID) return i;
        }
        return -1;
    }

    private void getListRequestMasterWhenAllDataFail(JSONObject eWalletDataObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JSONArray eWalletData = eWalletDataObject.getJSONObject("data").getJSONObject("eWallet_data").getJSONArray("failList");
        for (int i = 0; i<eWalletData.length(); i++) {
            JSONObject successDataElement = (JSONObject) eWalletData.get(i);
            EWalletDataDTO eWalletDataDTO = objectMapper.readValue(successDataElement.toString(),EWalletDataDTO.class);
            int index = findRequestMasterByObjectId(eWalletDataDTO.getObjectId(),resultAfterChangeStatusConvertList);
            resultAfterChangeStatusConvertList.set(index,CheckerMapper.eWalletDtoToRequestMaster(eWalletDataDTO,resultAfterChangeStatusConvertList.get(index),
                    Constant.MessageApi.FAIL,Constant.Result.REJECTED.toString()));
        }
    }

    private Iterable<RequestMaster> changeStatusToAPPROVED(List<RequestMaster> requestMasterListBeforeCallEWallet) {
        return checkerRepository.saveAll(requestMasterListBeforeCallEWallet);
    }

}
