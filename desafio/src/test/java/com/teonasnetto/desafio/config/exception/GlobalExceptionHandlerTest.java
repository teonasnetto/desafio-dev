package com.teonasnetto.desafio.config.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleMultipartExceptionReturnsBadRequest() {
        MultipartException ex = new MultipartException("File is missing");
        ResponseEntity<String> response = handler.handleMultipartException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("File is required", response.getBody());
    }

    @Test
    void handleAllExceptionsReturnsInternalServerError() {
        Exception ex = new Exception("Unexpected error");
        ResponseEntity<String> response = handler.handleAllExceptions(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred: Unexpected error", response.getBody());
    }

    @Test
    void handleAllExceptionsReturnsInternalServerErrorWithNullMessage() {
        Exception ex = new Exception();
        ResponseEntity<String> response = handler.handleAllExceptions(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred: null", response.getBody());
    }
}