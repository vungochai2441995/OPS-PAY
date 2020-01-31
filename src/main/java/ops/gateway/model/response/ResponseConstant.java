package ops.gateway.model.response;

import ops.gateway.util.Constant;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ResponseConstant {
    public static ResponseDTO responseOK(String methodName){return new  ResponseDTO(Constant.MessageApi.SUCCESS,Constant.ErrorCodeApi.SUCCESS,null,methodName);}
    public static ResponseDTO responseError400(String errorName,List<String> details){return new  ResponseDTO(Constant.ErrorCodeApi.INVALID_INPUT,errorName,details);}
    public static ResponseDTO responseError401(List<String> details){return new  ResponseDTO(Constant.MessageApi.PAY_MAKER_CHECKER_FALSE,Constant.ErrorCodeApi.UNAUTHORIZED,details);}
}
