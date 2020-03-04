package pl.martyna.catering.app.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CateringLoggingAspect {

    private final Logger LOG = LogManager.getLogger(getClass());

    @Before("execution(* pl.martyna.catering.app.controller.*.*.*(..)))")
    public void beforeRequestHandling(JoinPoint joinPoint){
        LOG.info("Handling request: " + joinPoint.getSignature().getName());
    }
}

