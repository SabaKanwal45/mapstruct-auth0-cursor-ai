package com.mapstruct.demo.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BadRequestExceptionTest {

    @Test
    public void testBadRequestException() {
        // Create an instance of BadRequestException
        BadRequestException exception = new BadRequestException("Bad request");

        // Assert values
        assertEquals("Bad request", exception.getMessage());
    }
}
