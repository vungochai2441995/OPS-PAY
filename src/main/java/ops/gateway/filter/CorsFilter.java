package ops.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import ops.gateway.model.response.common.ResponseConstant;
import ops.gateway.model.response.CommonResponse;
import ops.gateway.util.Constant;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
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
    private String apiKey = Constant.FilterConstant.X_API_KEY;

    private String apiSecret = Constant.FilterConstant.SECRET;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Logger logger = LoggerFactory.getLogger(CorsFilter.class);
        List<String> list = new CopyOnWriteArrayList<>();
        list.add(Constant.MessageApi.PAY_MAKER_CHECKER_FALSE);
        try {
            String requestApiKey = request.getHeader("api_key");
            String requestApiSecret = request.getHeader("api_secret");
            if (!requestApiKey.equals("ops-pay-key") || !requestApiSecret.equals("ops-pay-secret")) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    response.getOutputStream().write(restResponseBytes(ResponseConstant.responseError401(list)));
                    return;
                }else {
                    filterChain.doFilter(request, response);
                }
        }catch (RuntimeException e) {
            logger.error(e.toString());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getOutputStream().write(restResponseBytes(ResponseConstant.responseError401(list)));
            return;

        }

    }

    private byte[] restResponseBytes(CommonResponse commonResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(commonResponse);
        return serialized.getBytes();
    }

}
