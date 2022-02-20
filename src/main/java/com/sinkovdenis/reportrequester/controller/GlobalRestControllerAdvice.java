package com.sinkovdenis.reportrequester.controller;

import com.sinkovdenis.reportrequester.exception.GenericReportRequesterException;
import com.sinkovdenis.reportrequester.exception.IllegalRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> onError(ConstraintViolationException error) {
        log.debug(error.getMessage());
        GenericReportRequesterException exception = new GenericReportRequesterException("ConstraintViolationException occurred: " +
                error.getMessage(), error);
        return ResponseEntity
                .status(exception.getHttpCode())
                .body(exception.getMessage());
    }

    @ExceptionHandler(IllegalRequestException.class)
    public ResponseEntity<String> onError(IllegalRequestException error) {
        log.debug(error.getMessage());
        return ResponseEntity
                .status(error.getHttpCode())
                .body(error.getMessage());
    }
}
