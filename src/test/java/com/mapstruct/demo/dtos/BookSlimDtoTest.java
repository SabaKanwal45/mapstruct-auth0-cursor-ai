package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookSlimDtoTest {

    @Test
    public void testBookSlimDto() {
        // Create an instance of BookSlimDto
        BookSlimDto bookSlimDto = new BookSlimDto();

        // Set values
        bookSlimDto.setId(1);
        bookSlimDto.setTitle("Test Title");

        // Assert values
        assertEquals(1, bookSlimDto.getId());
        assertEquals("Test Title", bookSlimDto.getTitle());
    }
}
