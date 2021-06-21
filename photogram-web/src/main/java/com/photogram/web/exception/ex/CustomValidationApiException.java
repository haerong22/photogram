package com.photogram.web.exception.ex;

import org.springframework.validation.BindingResult;

public class CustomValidationApiException extends RuntimeException{

    private final BindingResult bindingResult;

    public CustomValidationApiException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
