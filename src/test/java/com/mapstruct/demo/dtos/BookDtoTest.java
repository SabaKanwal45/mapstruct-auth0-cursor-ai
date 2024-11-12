package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class BookDtoTest {

    @Test
    public void testBookDto() {
        // Create an instance of BookDto
        BookDto bookDto = new BookDto();
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(2);
        authorDto.setName("Author Name");
        bookDto.setAuthors(Set.of(authorDto));
        // Set values
        bookDto.setId(1);
        bookDto.setTitle("Book Title");

        // Assert values
        assertEquals(1, bookDto.getId());
        assertEquals("Book Title", bookDto.getTitle());
        Set<AuthorDto> authorDtos = bookDto.getAuthors();
        AuthorDto authorDto2 = authorDtos.iterator().next();
        assertEquals(1, bookDto.getAuthors().size());
        assertEquals(2, authorDto2.getId());
        assertEquals("Author Name", authorDto2.getName());
    }
}
