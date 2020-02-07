package ops.gateway.util.Mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ops.gateway.entities.RequestMaster;
import ops.gateway.model.dto.EWalletDataDTO;
import ops.gateway.model.request.CheckerRequest;
import ops.gateway.model.request.EWalletServiceRequest;
import ops.gateway.util.Constant;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CheckerMapper {
    public static List<EWalletServiceRequest> CheckerRequestToEWalletRequestMapper(List<CheckerRequest> checkerRequestList) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<EWalletServiceRequest> eWalletServiceRequestList = new CopyOnWriteArrayList<>();
        if (checkerRequestList != null && !checkerRequestList.isEmpty()) {
            for (CheckerRequest checkerRequest: checkerRequestList) {
                EWalletServiceRequest eWalletServiceRequest = new EWalletServiceRequest();
                String requestBody = new String();
                try {
                    String requestServiceId = "1234";
                    requestBody = objectMapper.writeValueAsString(checkerRequest);
                    eWalletServiceRequest.setObjectID(checkerRequest.getObjectId());
                    eWalletServiceRequest.setRequestBody(requestBody);
                    eWalletServiceRequest.setRequestServiceId(requestServiceId);
                    eWalletServiceRequest.setRequestTime(new Date());
                    eWalletServiceRequest.setOriginalTransId(checkerRequest.getOriginalTransId());
                    eWalletServiceRequestList.add(eWalletServiceRequest);
                }catch (JsonProcessingException ex) {
                    System.out.println(ex);
                }
            }
        }
        return eWalletServiceRequestList;
    }

    public static List<RequestMaster> checkerRequestListToRequestMasterList(List<CheckerRequest> checkerRequestList) {
        List<RequestMaster> requestMasterList = new CopyOnWriteArrayList<>();
        if (checkerRequestList != null && !checkerRequestList.isEmpty()) {
            for (CheckerRequest checkerRequest : checkerRequestList) {
                RequestMaster requestMaster = new RequestMaster();
                requestMaster.setObjectId(checkerRequest.getObjectId());
                requestMaster.setCheckerName(checkerRequest.getUserName());
                requestMaster.setCheckerId(checkerRequest.getUserId());
                requestMaster.setCheckedTime(new Date());
                requestMaster.setStatus(Constant.Status.APPROVED.toString());
                requestMasterList.add(requestMaster);
            }
        }
        return requestMasterList;
    }

    public static RequestMaster eWalletDtoToRequestMaster (EWalletDataDTO eWalletDataDTO, RequestMaster original, String comment,String result) {
        RequestMaster requestMaster = new RequestMaster();
        requestMaster.setStatus(Constant.Status.APPROVED.toString());
        requestMaster.setCheckerId(original.getCheckerId());
        requestMaster.setCheckedTime(new Date());
        requestMaster.setCheckerName(original.getCheckerName());
        requestMaster.setObjectId(eWalletDataDTO.getObjectId());
        requestMaster.setResponseTime(eWalletDataDTO.getResponseTime());
        requestMaster.setComment(eWalletDataDTO.getComment());
        requestMaster.setResponseTransId(eWalletDataDTO.getResponseTransId());
        requestMaster.setComment(comment);
        requestMaster.setResult(result);
        return requestMaster;
    }

}
