package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorAllDtoTest {

    @Test
    public void testAuthorAllDto() {
        // Create an instance of AuthorAllDto
        AuthorAllDto authorAllDto = new AuthorAllDto();

        // Set values
        authorAllDto.setId(1);
        authorAllDto.setName("Author Name");

        // Assert values
        assertEquals(1, authorAllDto.getId());
        assertEquals("Author Name", authorAllDto.getName());
    }
}
