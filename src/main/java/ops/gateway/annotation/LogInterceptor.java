package ops.gateway.annotation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class LogInterceptor {
    private Logger logger = null;

    @Before(value = "ops.gateway.annotation.LogManager.performanceLog()"
            + "&& target(bean) "
            + "&& @annotation(ops.gateway.annotation.Loggable)",
            argNames = "bean")
    public void endpointBefore(JoinPoint p,Object bean) {

        Object[] signatureArgs = p.getArgs();


        if (logger == null){
            logger = LoggerFactory.getLogger(bean.getClass().getName());
        }
        ObjectMapper mapper = new ObjectMapper();
        try {

            if (signatureArgs[0] != null) {
                System.out.println("\nRequest object: \n" + mapper.writeValueAsString(signatureArgs[0]));
            }
        } catch (JsonProcessingException e) {
        }

    }

    @AfterReturning(value = "ops.gateway.annotation.LogManager.performanceLog()"
            + "&& target(bean) "
            + "&& @annotation(ops.gateway.annotation.Loggable)",
            returning = "result")
    public void resultLog(JoinPoint jp, Object bean, ResponseEntity result) throws Throwable {
        System.out.println(result.getClass());
        String resultEntity = new ObjectMapper().writeValueAsString(result);
        System.out.println(resultEntity);
    }


}