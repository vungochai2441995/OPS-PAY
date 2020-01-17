package ops.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ops.gateway.response.Response;
import ops.gateway.security.SecurityConstants;
import org.springframework.http.HttpStatus;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JwtUltis {
    public static Response verifyToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER);
        if (token == null || !token.startsWith(SecurityConstants.PREFIX)) {
            return new Response("Không giải nén được Token", HttpStatus.UNAUTHORIZED,"code 401001");
        }
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token.replace(SecurityConstants.PREFIX, ""))
                    .getBody();
        }catch (RuntimeException e) {
            return new Response("Không giải nén được Token", HttpStatus.UNAUTHORIZED,"code 401001");
        }
        return new Response("Token đúng", HttpStatus.OK,"");
    }
}
