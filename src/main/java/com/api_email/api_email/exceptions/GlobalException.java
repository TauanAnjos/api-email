package com.api_email.api_email.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorExceptionBase> EmailInvalid(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorExceptionBase error = new ErrorExceptionBase(HttpStatus.BAD_REQUEST, "Erro de validação", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EmailServiceException.class)
    public ResponseEntity<ErrorExceptionBase> EmailInternalErro(EmailServiceException ex){
        var error = new ErrorExceptionBase(HttpStatus.INTERNAL_SERVER_ERROR, "Falha ao enviar e-mail","Tente novamente mais tarde");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
