package com.example.testappwithtests_springboot.util.aspect.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static ch.qos.logback.core.pattern.color.ANSIConstants.*;

/**
 * @author Artem Kovalov on 01.10.2023
 */
@Aspect
@Component
@Slf4j
public record LoggingControllerClassesAspect() {

    private static final String ANSI_GREEN = ESC_START + GREEN_FG + ESC_END;
    private static final String ANSI_RESET = ESC_START + DEFAULT_FG + ESC_END;

    @Pointcut("execution(public * com.example.testappwithtests_springboot.web.*.*(..))")
    public void callAtControllersPublicMethods() {
    }

    @Before("callAtControllersPublicMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        log.info(ANSI_GREEN + "Controller: " + methodName + " - start." + ANSI_RESET);
    }

    @After(value = "callAtControllersPublicMethods()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        log.info(ANSI_GREEN + "Controller: " + methodName + " - end." + ANSI_RESET);
    }

}
