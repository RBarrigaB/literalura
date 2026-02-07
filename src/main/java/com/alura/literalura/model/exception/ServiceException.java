package com.alura.literalura.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {

    private final HttpStatus statusCode;

    public ServiceException(String message) {
        super(message);
        this.statusCode = HttpStatus.BAD_REQUEST;
    }

    public ServiceException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
