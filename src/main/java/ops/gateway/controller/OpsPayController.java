package ops.gateway.controller;

import ops.gateway.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay-maker-checker")
public class OpsPayController {
    @PostMapping("")
    public ResponseEntity<?> checkAuthen() {
        System.out.println("someone here");
        ResponseDTO responseDTO = new  ResponseDTO("Thành công","200000","checkAuthen");
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
