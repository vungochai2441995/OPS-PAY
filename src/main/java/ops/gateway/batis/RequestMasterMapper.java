package ops.gateway.batis;

import ops.gateway.entities.RequestMaster;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RequestMasterMapper {
//    @Select({"SELECT * FROM request_master WHERE OBJECT_ID = #{id}"})
//    @Results({
//            @Result(property = "objectId", column = "OBJECT_ID"),
//            @Result(property = "originalTransId", column = "ORIGINAL_TRANS_ID"),
//            @Result(property = "makerRequestId", column = "MAKER_REQUEST_ID"),
//            @Result(property = "makerId", column = "MAKER_ID"),
//            @Result(property = "makerName", column = "MAKER_NAME"),
//            @Result(property = "makedTime", column = "MAKED_TIME"),
//            @Result(property = "transactionType", column = "TRANSACTION_TYPE"),
//            @Result(property = "actionType", column = "ACTION_TYPE"),
//            @Result(property = "data", column = "DATA"),
//            @Result(property = "checkerId", column = "CHECKER_ID"),
//            @Result(property = "checkerName", column = "CHECKER_NAME"),
//            @Result(property = "checkedTime", column = "CHECKED_TIME"),
//            @Result(property = "status", column = "STATUS"),
//            @Result(property = "result", column = "RESULT"),
//            @Result(property = "responseTime", column = "RESPONSE_TIME"),
//            @Result(property = "responseTransId", column = "RESPONSE_TRANS_ID"),
//    })
    RequestMaster selectOne(long id);


    @Insert("INSERT INTO request_master(OBJECT_ID, ORIGINAL_TRANS_ID, MAKER_REQUEST_ID, MAKER_ID, MAKER_NAME," +
            "MAKED_TIME, TRANSACTION_TYPE, ACTION_TYPE, DATA, CHECKER_ID, CHECKER_NAME, CHECKED_TIME, STATUS, RESULT," +
            "COMMENT, RESPONSE_TIME, RESPONSE_TRANS_ID) VALUES(#{objectId}, #{originalTransId}, #{makerRequestId}, #{makerId}," +
            " #{makerName}, #{makedTime}, #{transactionType}, #{actionType}, #{data}, #{checkerId}, #{checkerName}, #{checkedTime}, #{status}," +
            " #{result}, #{comment}, #{responseTime}, #{responseTransId})")
    @Options(useGeneratedKeys = true, keyColumn = "OBJECT_ID", keyProperty = "objectId")
    int insertRequestMaster(RequestMaster requestMaster);

}
