package com.tamdt.pmbackend.common.exceptionHandler;

public class BusinessNotFoundException extends RuntimeException {

    String message;

    public BusinessNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
