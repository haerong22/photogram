package com.photogram.web.handler.aop;

import com.photogram.web.handler.ex.CustomValidationApiException;
import com.photogram.web.handler.ex.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Arrays;

@Component
@Aspect
public class ValidationAdvice {

    @Around("execution(* com.photogram.web.controller.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint pjp) throws Throwable {

        Object[] args = pjp.getArgs();
        Arrays.stream(args)
                .filter(arg -> arg instanceof BindingResult)
                .forEach(arg -> {
                    BindingResult bindingResult = (BindingResult) arg;
                    if (bindingResult.hasFieldErrors()) {
                        throw new CustomValidationApiException("유효성 검사 실패", bindingResult);
                    }
                });

        return pjp.proceed();
    }

    @Around("execution(* com.photogram.web.controller.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint pjp) throws Throwable {

        Object[] args = pjp.getArgs();
        Arrays.stream(args)
                .filter(arg -> arg instanceof BindingResult)
                .forEach(arg -> {
                    BindingResult bindingResult = (BindingResult) arg;
                    if (bindingResult.hasFieldErrors()) {
                        throw new CustomValidationException("유효성 검사 실패", bindingResult);
                    }
                });

        return pjp.proceed();
    }
}
