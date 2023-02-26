package com.masson.people.business.exception;

import com.masson.people.business.exception.response.DefaultError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        if(e.getCause() instanceof ZipCodeNotFoundException){
            return respond(HttpStatus.NOT_FOUND, "CEP não encontrado");
        }
        return respond(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado");
    }

    private ResponseEntity respond(HttpStatus status, String message){
        return new ResponseEntity(new DefaultError(status.value(), message), status);
    }

}
