package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserPostDtoTest {

    @Test
    public void testUserPostDto() {
        // Create an instance of UserPostDto
        UserPostDto userPostDto = new UserPostDto();

        // Set values
        userPostDto.setId(1);
        userPostDto.setEmail("test@example.com");
        userPostDto.setPassword("password123");
        userPostDto.setName("John");
        userPostDto.setSurname("Doe");

        // Assert values
        assertEquals(1, userPostDto.getId());
        assertEquals("test@example.com", userPostDto.getEmail());
        assertEquals("password123", userPostDto.getPassword());
        assertEquals("John", userPostDto.getName());
        assertEquals("Doe", userPostDto.getSurname());
    }
}
