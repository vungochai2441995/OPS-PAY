package ops.gateway.util;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    public enum Status {
        PENDING("PENDING"), APPROVED("APPROVED"), REJECTED("REJECTED");
        private String value;

        Status(String value) {
            this.value = value;
        }

        @Override
        public String toString(){
            return this.value;
        }
    }

    public enum Result {
        PENDING("PENDING"), PROCESSING("PROCESSING"), SUCCESS("SUCCESS") , REJECTED("REJECTED");
        private String value;

        Result(String value) {
            this.value = value;
        }

        @Override
        public String toString(){
            return this.value;
        }
    }

    public static class Logging {
        public static final String LOG_REQUEST_BY_CLASS_AND_METHOD = "Call method: %s | Request object: %s";
        public static final String LOG_RESPONSE_BY_CLASS_AND_METHOD = "Call method: %s | Response object: %s";
    }

    public static class ErrorCodeApi {
        public static final String SUCCESS = "200000";
        public static final String UNAUTHORIZED = "401101";
        public static final String INVALID_INPUT = "400001";
    }

    public static class MessageApi {
        public static final String SUCCESS = "Thành công";
        public static final String FAIL = "Không thành công";
        public static final String PAY_MAKER_CHECKER_FALSE = "Sai key connect tại pay-maker-checker";
        public static final String VALIDATE_NULL = "Không được null";
        public static final String VALIDATE_MAX_LENGTH_20 = "không được vượt quá 20 ký tự";
        public static final String VALIDATE_SPECIAL_CHARACTER = "không được chứa ký tự đặc biệt";
    }

    public class FilterConstant {
        public static final String HEADER_AUTHENTICATION = "Authorization";
        public static final String X_API_KEY = "api_key";
        public static final String SECRET = "api_secret";
    }
}
