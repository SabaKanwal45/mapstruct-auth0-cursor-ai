package com.mapstruct.demo.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorResponseTest {

    @Test
    public void testErrorResponse() {
        String message = "Error message";
        int status = 400;
        ErrorResponse errorResponse = new ErrorResponse(status, message);

        assertEquals(message, errorResponse.getMessage());
        assertEquals(status, errorResponse.getStatus());
    }

    @Test
    public void testSetMessage() {
        ErrorResponse errorResponse = new ErrorResponse(0, "");
        String message = "New error message";
        errorResponse.setMessage(message);
        assertEquals(message, errorResponse.getMessage());
    }

    @Test
    public void testSetStatus() {
        ErrorResponse errorResponse = new ErrorResponse(0, "");
        int status = 404;
        errorResponse.setStatus(status);
        assertEquals(status, errorResponse.getStatus());
    }
}
