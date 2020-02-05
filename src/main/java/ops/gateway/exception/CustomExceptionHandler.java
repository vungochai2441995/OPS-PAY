package ops.gateway.exception;

import ops.gateway.model.response.common.ResponseConstant;
import ops.gateway.model.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        String[] arr = ex.getLocalizedMessage().split(",");
        List<String> details = new ArrayList<>();
        if (arr != null && arr.length > 0){
            for (String s : arr) {
                String result = s.substring(s.indexOf("["));
                details.add(result);
            }
        }
        details = details.stream()
                .distinct()
                .collect(Collectors.toList());
        CommonResponse error = ResponseConstant.responseError400("Validated Fail",details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
