package ops.gateway.controller;

import ops.gateway.model.request.CheckerRequest;
import ops.gateway.model.request.MakerRequest;
import ops.gateway.model.request.RequestDTO;
import ops.gateway.annotation.Loggable;
import ops.gateway.model.response.CommonResponse;
import ops.gateway.model.response.common.ResponseConstant;
import ops.gateway.service.ICheckerService;
import ops.gateway.service.impl.MakerService;
import ops.gateway.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ICheckerService iCheckerService;

    @Autowired
    private MakerService makerService;

    @Loggable
    @PostMapping()
    public ResponseEntity<?> PayMakerChecker(@RequestBody(required = false) @Valid RequestDTO requestDTO, BindingResult bindingResult) {
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

    @Loggable
    @PostMapping("/maker")
    public ResponseEntity<?> maker(@RequestBody(required = false) @Valid List<MakerRequest> makerRequestList) {
        if (makerRequestList == null) {
            List<String> errorDetail = new CopyOnWriteArrayList<>();
            errorDetail.add(Constant.MessageApi.VALIDATE_NULL);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseConstant.responseError400(Constant.MessageApi.VALIDATE_NULL,errorDetail));
        }
        CommonResponse commonResponse = makerService.createMaker(makerRequestList);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @Loggable
    @PostMapping("/checker")
    public ResponseEntity<?> checker(@RequestBody List<CheckerRequest> checkerRequestList) {
        ResponseEntity<String> eWalletServiceResponse = iCheckerService.checker(checkerRequestList);
        return ResponseEntity.status(HttpStatus.OK).body(eWalletServiceResponse.getBody());
    }

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.status(HttpStatus.OK).body(iCheckerService.test());
    }
}
