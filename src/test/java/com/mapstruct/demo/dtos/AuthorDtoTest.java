package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorDtoTest {

    @Test
    public void testAuthorDto() {
        // Create an instance of AuthorDto
        AuthorDto authorDto = new AuthorDto();

        // Set values
        authorDto.setId(1);
        authorDto.setName("Author Name");

        // Assert values
        assertEquals(1, authorDto.getId());
        assertEquals("Author Name", authorDto.getName());
    }
}
