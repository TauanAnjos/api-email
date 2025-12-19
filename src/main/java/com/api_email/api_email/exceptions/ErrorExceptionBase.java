package com.api_email.api_email.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorExceptionBase {
    HttpStatus status;
    String error;
    String message;

    public ErrorExceptionBase(HttpStatus status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
