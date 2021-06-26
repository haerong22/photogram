package com.photogram.web.config.auth;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class PrincipalDetailsAspect {

    @Around("execution(* com.photogram.web.controller.auth.*Controller.*(..))")
    public Object principalDetails(ProceedingJoinPoint pjp) throws Throwable {

        Object result = pjp.proceed();
        PrincipalDetails principalDetails =
                (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Arrays.stream(pjp.getArgs())
                .filter(v -> v instanceof Model)
                .forEach(v -> {
                    ((Model) v).addAttribute("loginUserId", principalDetails.getUser().getId());
                });

        return result;
    }
}
