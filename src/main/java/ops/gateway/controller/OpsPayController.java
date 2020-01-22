package ops.gateway.controller;

import ops.gateway.IO.request.RequestDTO;
import ops.gateway.annotation.Loggable;
import ops.gateway.IO.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pay-maker-checker")
public class OpsPayController {
    @Loggable
    @GetMapping
    public ResponseEntity<?> checkAuthen(@RequestBody @Validated RequestDTO requestDTO, BindingResult bindingResult) {
        ResponseDTO responseDTO = new  ResponseDTO("Thành công","200000","checkAuthen");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new  ResponseDTO(bindingResult.getFieldError().getDefaultMessage(),"400001"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    @Loggable
    @GetMapping("/t")
    public ResponseEntity<?> test() {
        ResponseDTO responseDTO = new  ResponseDTO("Thành công","200000","checkAuthen");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
