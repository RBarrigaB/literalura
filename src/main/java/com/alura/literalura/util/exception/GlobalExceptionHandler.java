package com.alura.literalura.util.exception;

import com.alura.literalura.model.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String, Object>> handleServiceException(ServiceException ex) {

        HttpStatus status = ex.getStatusCode() != null ? ex.getStatusCode() : HttpStatus.NOT_FOUND;

        Map<String, Object> errorDetails = Map.of(
                "error", ex.getMessage()
        );

        return new ResponseEntity<>(errorDetails, status);
    }
}