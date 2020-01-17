package ops.gateway.security;

import io.jsonwebtoken.Claims;
import ops.gateway.response.Response;
import ops.gateway.util.JwtUltis;
import org.springframework.security.authentication.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ApiJWTAuthorizationFilter extends BasicAuthenticationFilter {

    ApiJWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Response result = JwtUltis.verifyToken(request);
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(null,result);
        SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);
        chain.doFilter(request, response);
    }

}
