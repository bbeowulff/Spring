package chapter6.Aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(1) // executed BEFORE LoggingAspect (which is @Order(2))
public class SecurityAspect {

    private final Logger logger = Logger.getLogger(SecurityAspect.class.getName());

    // This uses the same pointcut as LoggingAspect so both form an execution chain
    @Around("@annotation(chapter6.Annotations.ToLog)")
    public Object secure(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        logger.info("[SecurityAspect] Checking security for method: " + methodName);

        // Here you could throw if not allowed
        // if (!authorized) throw new SecurityException("Not allowed");

        Object result = joinPoint.proceed();

        logger.info("[SecurityAspect] Security check passed for method: " + methodName);
        return result;
    }
}

