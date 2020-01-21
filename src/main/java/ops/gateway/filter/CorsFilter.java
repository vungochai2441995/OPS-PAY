package ops.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import ops.gateway.response.ResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getOutputStream().write(restResponseBytes(new ResponseDTO("Sai key connect tại pay-maker-checker","401101")));
                    return;

//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    ResponseDTO responseDTO = new ResponseDTO("Sai key connect tại pay-maker-checker","401101");
//                    String json = new ObjectMapper().writeValueAsString(responseDTO);
//                    response.getWriter().write(json);
//                    response.flushBuffer();
//                    return ;
                }else {
                    filterChain.doFilter(request, response);
                }
        }catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().write(restResponseBytes(new ResponseDTO("Sai key connect tại pay-maker-checker","401101")));
            return;

        }

    }

    private byte[] restResponseBytes(ResponseDTO responseDto) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(responseDto);
        return serialized.getBytes();
    }

}
