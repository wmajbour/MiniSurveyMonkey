package org.MiniSurveyMonkey.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect Logger Class
 */

@Aspect
@Component
public class RepositoryLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(RepositoryLoggingAspect.class);

    @Around("execution(* org.MiniSurveyMonkey.Repo.*.*(..))")
    public Object logRepositoryMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        log.info("Entering method: {}", methodName);

        Object result = joinPoint.proceed();

        log.info("Exiting method: {}", methodName);
        return result;
    }
}
