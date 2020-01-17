package ops.gateway.controller;

import ops.gateway.response.Response;
import ops.gateway.util.JwtUltis;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay-maker-checker")
public class OpsPayController {

    @GetMapping("")
    public Object checkAuthen() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object response = auth.getCredentials();
        return response;
    }
}
