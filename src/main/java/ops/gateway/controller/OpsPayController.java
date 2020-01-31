package ops.gateway.controller;

import lombok.SneakyThrows;
import ops.gateway.model.request.RequestDTO;
import ops.gateway.annotation.Loggable;
import ops.gateway.model.response.ResponseConstant;
import ops.gateway.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping(value = "/pay-maker-checker",consumes = "application/json", produces = "application/json")
@Validated
public class OpsPayController {
    @SneakyThrows
    @Loggable
    @PostMapping()
    public ResponseEntity<?> checkAuthen(@RequestBody(required = false) @Valid RequestDTO requestDTO, BindingResult bindingResult) {
        String currentClass = this.getClass().getSimpleName();
        String methodName = new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName();
        if (requestDTO == null) {
            List<String> errorDetail = new CopyOnWriteArrayList<>();
            errorDetail.add(Constant.MessageApi.VALIDATE_NULL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseConstant.responseError400(Constant.MessageApi.VALIDATE_NULL,errorDetail));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ResponseConstant.responseOK(currentClass + "." + methodName));
    }

}
