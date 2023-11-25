package br.janioofi.system_gym.controllers;

import br.janioofi.system_gym.exception.BusinessException;
import br.janioofi.system_gym.exception.RecordNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RecordNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String constraintViolationException(ConstraintViolationException ex){
        return ex.getConstraintViolations().stream()
                .map(error -> error.getPropertyPath() + " " + error.getMessage())
                .reduce("", (acc, error) -> acc + error + "\n");
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String duplicateFoundException(BusinessException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String httpMessageNotReadableException(HttpMessageNotReadableException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String dataIntegrityViolationException(DataIntegrityViolationException ex) {
        return ex.getMostSpecificCause().getMessage();
    }

}
