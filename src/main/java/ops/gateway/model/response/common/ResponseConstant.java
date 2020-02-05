package ops.gateway.model.response.common;

import ops.gateway.model.response.CommonResponse;
import ops.gateway.model.response.MakerResponse;
import ops.gateway.util.Constant;

import java.util.List;

public class ResponseConstant {
    public static CommonResponse responseOK(String methodName){return new CommonResponse(Constant.MessageApi.SUCCESS,Constant.ErrorCodeApi.SUCCESS, methodName);}
    public static CommonResponse responseOK(String methodName, MakerResponse makerResponse){return new CommonResponse(Constant.MessageApi.SUCCESS,Constant.ErrorCodeApi.SUCCESS, makerResponse, methodName);}
    public static CommonResponse responseError400(String errorName, List<String> details){return new CommonResponse(Constant.ErrorCodeApi.INVALID_INPUT,errorName,details);}
    public static CommonResponse responseError401(List<String> details){return new CommonResponse(Constant.MessageApi.PAY_MAKER_CHECKER_FALSE,Constant.ErrorCodeApi.UNAUTHORIZED,details);}
}
