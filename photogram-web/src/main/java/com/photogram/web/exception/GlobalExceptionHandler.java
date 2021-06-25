package com.photogram.web.exception;

import com.photogram.web.dto.CommonResponse;
import com.photogram.web.exception.ex.CustomApiException;
import com.photogram.web.exception.ex.CustomException;
import com.photogram.web.exception.ex.CustomValidationApiException;
import com.photogram.web.exception.ex.CustomValidationException;
import com.photogram.web.utils.Script;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException exception) {
        if (exception.getBindingResult()  == null) {
            return Script.back(exception.getMessage());
        }
        Map<String, String> errors = getErrors(exception.getBindingResult());
        return Script.back(errors.toString());
    }

    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationException(CustomValidationApiException exception) {
        Map<String, String> errors = getErrors(exception.getBindingResult());
        return ResponseEntity.badRequest().body(
                CommonResponse.builder()
                        .code(-1)
                        .result(exception.getMessage())
                        .data(errors)
                        .build());
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException exception) {
        return ResponseEntity.badRequest().body(
                CommonResponse.builder()
                        .code(-1)
                        .result(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(CustomException.class)
    public String customException(CustomException exception) {
        return Script.back(exception.getMessage());
    }

    // ========================================================================
    private Map<String, String> getErrors(BindingResult bindingResult) {
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
