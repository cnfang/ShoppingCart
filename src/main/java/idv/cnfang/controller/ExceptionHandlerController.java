package idv.cnfang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import idv.cnfang.exception.DuplicateResourceException;
import idv.cnfang.exception.NotFoundException;
import idv.cnfang.utility.CustomApiError;

@RestControllerAdvice
public class ExceptionHandlerController {
    
    protected static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
    
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /* . . . . . . . . . . . . . EXCEPTION HANDLERS . . . . . . . . . . . . . . */
    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> duplicateResourceCreation(Exception exception) {
        logger.error("Request raised a " + exception.getClass().getSimpleName());
        return new ResponseEntity<>(new CustomApiError(HttpStatus.CONFLICT, "duplicate resource", exception), HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> resourceNotFound(Exception exception) {
        logger.error("Request raised a " + exception.getClass().getSimpleName());
        return new ResponseEntity<>(new CustomApiError(HttpStatus.NOT_FOUND, "resource not found", exception), HttpStatus.NOT_FOUND);
    }
}
