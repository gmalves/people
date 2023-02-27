package com.masson.people.business.exception;

import com.masson.people.business.exception.response.DefaultError;
import com.masson.people.business.exception.response.ErrorObject;
import com.masson.people.business.exception.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        String field = ex.getBindingResult().getFieldErrors().get(0).getField();
        return ResponseEntity.badRequest().body("Field: " + field + " Message: " + message);
/*
         JEITO CORRETO QUE NÃO FUNCIONA NEM NA BALA

        List<ErrorObject> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return ResponseEntity.badRequest().body(errorResponse);*/
    }

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatusCode status, List<ErrorObject> errors) {
        return new ErrorResponse("Requisição possui campos inválidos", status.value(),
                errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }
    private ResponseEntity respond(HttpStatus status, String message){
        return new ResponseEntity(new DefaultError(status.value(), message), status);
    }

}
