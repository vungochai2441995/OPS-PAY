package ops.gateway.util.Mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ops.gateway.entities.RequestMaster;
import ops.gateway.model.request.CheckerRequest;
import ops.gateway.model.request.EWalletServiceRequest;
import ops.gateway.model.response.CheckerResponse;
import ops.gateway.model.response.EWalletServiceResponse;

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

}
