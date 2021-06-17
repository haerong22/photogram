package com.photogram.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public Map<String, String> validationException(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(error -> {
            String field = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(field, errorMessage);
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            log.warn("Validation EXCEPTION ===> {} , URI ===> {}", errorMessage, request.getRequestURI());
        });

        return errors;
    }
}
