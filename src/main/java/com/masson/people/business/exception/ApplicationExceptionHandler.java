package com.masson.people.business.exception;

import com.masson.people.business.exception.response.DefaultError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e){
        logger.info("Application error " + e);
        if(e.getCause() instanceof ZipCodeNotFoundException){
            return respond(HttpStatus.NOT_FOUND, "CEP não encontrado");
        } else if (e instanceof RegisteredPeopleException) {
            return respond(HttpStatus.UNPROCESSABLE_ENTITY, "Pessoa já cadastrada");
        }else if (e instanceof PeopleNotFoundException) {
            return respond(HttpStatus.NOT_FOUND, "Pessoa não encontrada");
        }
        return respond(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado");
    }

    private ResponseEntity respond(HttpStatus status, String message){
        return new ResponseEntity(new DefaultError(status.value(), message), status);
    }

}
