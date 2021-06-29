package com.codeofeverything.backendmarketstaresearch.advice;

import com.codeofeverything.backendmarketstaresearch.exception.SymbolNotFoundException;
import com.codeofeverything.backendmarketstaresearch.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message) {
        log.error("Exception received: " + message);
        return new ErrorResponse(String.valueOf(httpStatus.value()), httpStatus.getReasonPhrase(), message);
    }


    @ExceptionHandler(SymbolNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleSymbolNotFoundException(SymbolNotFoundException ex) {
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        ex.printStackTrace();
        return errorResponse;
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse handleSymbolNotFoundException(HttpClientErrorException ex) {
        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, "Symbol not found.");
        ex.printStackTrace();
        return errorResponse;
    }
}

