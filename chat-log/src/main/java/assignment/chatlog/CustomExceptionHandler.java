package assignment.chatlog;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import exception.MessageNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

	/**
     * Constraint violation exception handler   
     * 
     * @param ex ConstraintViolationException
     *
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String>
        handleConstraintViolation(ConstraintViolationException ex) {
        return new ResponseEntity<String>(ex.getMessage().split(":")[1],HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String>
        handleConstraintViolation(IllegalArgumentException ex) {
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
    
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<String>
        handleConstraintViolation(MessageNotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
    
    
     
    

}
