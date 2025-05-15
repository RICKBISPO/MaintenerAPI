package com.simples.maintainer.controllers;

import com.simples.maintainer.dtos.error.ApiError;
import com.simples.maintainer.exceptions.AlreadyExistsException;
import com.simples.maintainer.exceptions.notfound.NotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception e) {
        var apiError = getApiError(HttpStatus.INTERNAL_SERVER_ERROR, List.of(e.getMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException e) {
        var apiError = getApiError(HttpStatus.NOT_FOUND, List.of(e.getMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiError> handleAlreadyExistsException(AlreadyExistsException e) {
        var apiError = getApiError(HttpStatus.CONFLICT, List.of(e.getMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleInvalidException(
            MethodArgumentNotValidException e
    ) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        var apiError = getApiError(HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    private ApiError getApiError(HttpStatus httpStatus, List<String> errors) {
        return new ApiError(
                LocalDateTime.now(),
                httpStatus.value(),
                httpStatus.name(),
                errors
        );
    }

}

