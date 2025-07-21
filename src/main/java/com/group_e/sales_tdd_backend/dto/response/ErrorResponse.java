package com.group_e.sales_tdd_backend.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;
import java.util.Date;

@Getter
public class ErrorResponse {
    private final Date timestamp;
    private final Integer status;
    private final String error;
    private final Class<? extends Exception> exception;
    private final String message;
    private final String path;

    public ErrorResponse(
            HttpStatusCode httpStatusCode,
            Class<? extends Exception> exception,
            String message,
            String path
    ) {
        HttpStatus httpStatus = HttpStatus.valueOf(httpStatusCode.value());
        this.timestamp = Date.from(Instant.now());
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.exception = exception;
        this.message = message;
        this.path = path;
    }
}
