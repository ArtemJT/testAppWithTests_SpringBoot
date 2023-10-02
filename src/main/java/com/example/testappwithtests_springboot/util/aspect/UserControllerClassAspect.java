package com.example.testappwithtests_springboot.util.aspect;

import com.example.testappwithtests_springboot.util.exception.DateRangeException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author Artem Kovalov on 02.10.2023
 */
@Aspect
@Component
@Slf4j
public record UserControllerClassAspect() {

    @Pointcut("execution(public * com.example.testappwithtests_springboot.web.UserController.getAllByBirthDateRange(..))")
    public void callAtGetAllByBirthDateRangeMethod() {
    }

    @Before("callAtGetAllByBirthDateRangeMethod()")
    public void checkBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        LocalDate from = (LocalDate) args[0];
        LocalDate to = (LocalDate) args[1];
        if (from.isAfter(to)) {
            throw new DateRangeException("Date 'FROM' must be earlier than 'TO'");
        }
    }
}
