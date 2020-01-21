package ops.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
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
        return ResponseEntity.ok("Request OK");
    }
}
