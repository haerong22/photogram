package com.photogram.web.handler.ex;

import org.springframework.validation.BindingResult;

public class CustomValidationException extends RuntimeException{

    private final BindingResult bindingResult;

    public CustomValidationException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
