package ops.gateway.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogManager {

    @Pointcut("execution(* ops.gateway.controller.*.*(..))")
    public void auditLog() {}

    @Pointcut("execution(* ops.gateway.controller.*.*(..))")
    public void performanceLog(){
    }
}