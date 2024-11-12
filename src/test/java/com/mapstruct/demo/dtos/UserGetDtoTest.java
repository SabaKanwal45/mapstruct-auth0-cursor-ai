package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserGetDtoTest {

    @Test
    public void testUserGetDto() {
        // Create an instance of UserGetDto
        UserGetDto userGetDto = new UserGetDto();

        // Set values
        userGetDto.setId(1);
        userGetDto.setEmail("test@example.com");
        userGetDto.setName("John");
        userGetDto.setSurname("Doe");

        // Assert values
        assertEquals(1, userGetDto.getId());
        assertEquals("test@example.com", userGetDto.getEmail());
        assertEquals("John", userGetDto.getName());
        assertEquals("Doe", userGetDto.getSurname());
    }
}
