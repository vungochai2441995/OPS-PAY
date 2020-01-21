package ops.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import ops.gateway.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import com.google.gson.Gson;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CorsFilter implements Filter {
    @Value("${api_key}")
    private String apiKey;

    @Value("${api_secret}")
    private String apiSecret;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        ApiKeyVerifiRequestWrapper requestWrapper = new ApiKeyVerifiRequestWrapper(request);
        try {
            String requestApiKey = requestWrapper.getHeader("api_key");
            String requestApiSecret = requestWrapper.getHeader("api_secret");
                if (!requestApiKey.equals("ops-pay-key") || !requestApiSecret.equals("ops-pay-secret")) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getOutputStream().write(restResponseBytes(new Response("Sai key connect","401001",HttpStatus.UNAUTHORIZED)));
                    return;
                }
                filterChain.doFilter(request, response);
        }catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().write(restResponseBytes(new Response("Loi server","500",HttpStatus.INTERNAL_SERVER_ERROR)));
            return;

        }

    }

    private byte[] restResponseBytes(Response responseDto) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(responseDto);
        return serialized.getBytes();
    }

}
