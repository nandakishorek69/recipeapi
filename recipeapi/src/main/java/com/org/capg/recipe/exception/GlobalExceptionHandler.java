package com.org.capg.recipe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * The Class GlobalExceptionHandler is for exception handling.
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Global exception handler.
     *
     * @param ex
     * @param request
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionDetails> globalExceptionHandler(Exception ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Recipe not found exception.
     *
     * @param ex
     * @param request
     * @return the response entity
     */
    @ExceptionHandler(RecipeNotFoundException.class)
    public final ResponseEntity<ExceptionDetails> recipeNotFoundException(RecipeNotFoundException ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        ExceptionDetails exceptionDetails = new ExceptionDetails(new Date(), ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }
}
