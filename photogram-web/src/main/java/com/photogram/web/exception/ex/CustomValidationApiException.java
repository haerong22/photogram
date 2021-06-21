package com.photogram.web.exception.ex;

import org.springframework.validation.BindingResult;

public class CustomValidationApiException extends RuntimeException{

    private BindingResult bindingResult;

    public CustomValidationApiException(String message) {
        super(message);
    }

    public CustomValidationApiException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
