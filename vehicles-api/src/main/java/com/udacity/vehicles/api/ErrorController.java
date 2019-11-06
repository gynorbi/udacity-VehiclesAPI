package com.udacity.vehicles.api;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.udacity.vehicles.service.CarNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Implements the Error controller related to any errors handled by the Vehicles API
 */
@RestControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    private static final String DEFAULT_VALIDATION_FAILED_MESSAGE = "Validation failed";
    private static final String CAR_NOT_FOUND_MESSAGE = "Car not found";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(
                        Collectors.toList());

        ApiError apiError = new ApiError(DEFAULT_VALIDATION_FAILED_MESSAGE, errors);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value={CarNotFoundException.class} )
    public ResponseEntity<Object> handlerCarNotFoundException(CarNotFoundException ex, WebRequest request) {
        ApiError apiError = new ApiError(CAR_NOT_FOUND_MESSAGE,Arrays.asList(ex.getMessage()));
        return handleExceptionInternal(ex,apiError,new HttpHeaders(), HttpStatus.NOT_FOUND,request);
    }
}

