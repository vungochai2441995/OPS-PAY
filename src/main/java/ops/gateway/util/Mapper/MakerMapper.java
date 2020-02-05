package ops.gateway.util.Mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import ops.gateway.entities.RequestMaster;
import ops.gateway.model.request.MakerRequest;
import ops.gateway.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

public class MakerMapper {
    private static Logger logger = LoggerFactory.getLogger(MakerMapper.class.getName());
    public static RequestMaster GateWayRequestToRequestMasterEntity(MakerRequest makerRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        String data = "";
        try {
            data = objectMapper.writeValueAsString(makerRequest);
        } catch (IOException ex) {
            logger.info(ex.getMessage());
        }

        RequestMaster requestMaster = new RequestMaster();
        requestMaster.setOriginalTransId(makerRequest.getOriginalTransId());
        requestMaster.setMakerRequestId(makerRequest.getUserId().toString());
        requestMaster.setMakerId(makerRequest.getUserId());
        requestMaster.setMakerName(makerRequest.getUserName());
        requestMaster.setMakedTime(new Date());
        requestMaster.setTransactionType(makerRequest.getTransactionType());
        requestMaster.setActionType("create");
        requestMaster.setData(data);
        requestMaster.setCheckerId(null);
        requestMaster.setCheckerName(null);
        requestMaster.setCheckedTime(null);
        requestMaster.setStatus(Constant.Status.PENDING.toString());
        requestMaster.setResult(Constant.Result.PROCESSING.toString());
        requestMaster.setComment(null);
        requestMaster.setResponseTime(null);
        requestMaster.setResponseTransId(null);

        return requestMaster;
    }
}
