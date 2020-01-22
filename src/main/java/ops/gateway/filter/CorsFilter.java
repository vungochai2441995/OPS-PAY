package ops.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import ops.gateway.IO.response.ResponseDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import org.slf4j.Logger;

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

        Logger logger = LoggerFactory.getLogger(CorsFilter.class);
        try {
            String requestApiKey = request.getHeader("api_key");
            String requestApiSecret = request.getHeader("api_secret");
                if (!requestApiKey.equals("ops-pay-key") || !requestApiSecret.equals("ops-pay-secret")) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getOutputStream().write(restResponseBytes(new ResponseDTO("Sai key connect tại pay-maker-checker","401101")));
                    return;
                }else {
                    filterChain.doFilter(request, response);
                }
        }catch (RuntimeException e) {
            logger.error(e.toString());
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
