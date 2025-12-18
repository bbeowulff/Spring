package chapter6.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2) // Lower = earlier. SecurityAspect is @Order(1)
public class LoggingAspect {

    private final Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    // ─────────────────────────────────────────────────────────────────────────
    // STEP 1 – Example 1:
    // Simple @Around logging before/after ANY method in services package
    //
    // @Around("execution(* chapter6.Services.*.*(..))")
    // public void simpleLog(ProceedingJoinPoint joinPoint) throws Throwable {
    //     logger.info("STEP1 - Method will execute");
    //     joinPoint.proceed();
    //     logger.info("STEP1 - Method executed");
    // }
    // ─────────────────────────────────────────────────────────────────────────


    // ─────────────────────────────────────────────────────────────────────────
    // STEP 2 – Example 2:
    // Log method name, parameters, and returned value (but don’t modify them)
    //
    // @Around("execution(* chapter6.Services.*.*(..))")
    // public Object loggingWithParams(ProceedingJoinPoint joinPoint) throws Throwable {
    //     String methodName = joinPoint.getSignature().getName();
    //     Object[] args = joinPoint.getArgs();
    //
    //     logger.info("STEP2 - Method " + methodName +
    //             " with params " + Arrays.asList(args) + " will execute");
    //
    //     Object result = joinPoint.proceed();
    //
    //     logger.info("STEP2 - Method executed and returned " + result);
    //     return result;
    // }
    // ─────────────────────────────────────────────────────────────────────────


    // ─────────────────────────────────────────────────────────────────────────
    // STEP 3 – Example 3:
    // Change parameters and change returned value
    //
    // @Around("execution(* chapter6.Services.*.*(..))")
    // public Object loggingAndAltering(ProceedingJoinPoint joinPoint) throws Throwable {
    //     String methodName = joinPoint.getSignature().getName();
    //     Object[] args = joinPoint.getArgs();
    //
    //     logger.info("STEP3 - Method " + methodName +
    //             " with ORIGINAL params " + Arrays.asList(args) + " will execute");
    //
    //     // Change the argument before calling the method
    //     Comment changed = new Comment();
    //     changed.setAuthor("Aspect");
    //     changed.setText("Some other text!");
    //
    //     Object[] newArgs = { changed };
    //
    //     Object result = joinPoint.proceed(newArgs);
    //
    //     logger.info("STEP3 - Method executed and originally returned: " + result);
    //
    //     // We ignore original result and return "FAILED"
    //     return "FAILED";
    // }
    // ─────────────────────────────────────────────────────────────────────────


    // ─────────────────────────────────────────────────────────────────────────
    // STEP 4 – Example 4:
    // Intercept only annotated methods using @ToLog
    //
    // @Around("@annotation(chapter6.Annotations.ToLog)")
    // public Object loggingAnnotated(ProceedingJoinPoint joinPoint) throws Throwable {
    //     String methodName = joinPoint.getSignature().getName();
    //     Object[] args = joinPoint.getArgs();
    //
    //     logger.info("STEP4 - (@ToLog) Method " + methodName +
    //             " with params " + Arrays.asList(args) + " will execute");
    //
    //     Object result = joinPoint.proceed();
    //
    //     logger.info("STEP4 - (@ToLog) Method executed and returned " + result);
    //     return result;
    // }
    // ─────────────────────────────────────────────────────────────────────────


    // ─────────────────────────────────────────────────────────────────────────
    // STEP 5 – Example 5 + @AfterReturning:
    // Final active version:
    //   - Use @Around on @ToLog to log before/after
    //   - Use @AfterReturning on @ToLog to log the returned value
    //   - Also works with multiple aspects + @Order
    // ─────────────────────────────────────────────────────────────────────────

    @Around("@annotation(chapter6.Annotations.ToLog)")
    public Object logAroundAnnotated(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logger.info("[LoggingAspect @Around] Method " + methodName +
                " with params " + Arrays.asList(args) + " will execute");

        Object result = joinPoint.proceed();

        logger.info("[LoggingAspect @Around] Method " + methodName +
                " executed and returned " + result);

        return result;
    }

    @AfterReturning(
            value = "@annotation(chapter6.Annotations.ToLog)",
            returning = "returnedValue"
    )
    public void logAfterReturning(Object returnedValue) {
        logger.info("[LoggingAspect @AfterReturning] Method returned: " + returnedValue);
    }
}
