package com.mapstruct.demo.mappers;

import com.mapstruct.demo.dtos.BookDto;
import com.mapstruct.demo.dtos.BookSlimDto;
import com.mapstruct.demo.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BookMapperTest {

    private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        bookMapper = Mappers.getMapper(BookMapper.class);
    }

    @Test
    void testBookToBookSlimDto() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book Title");

        BookSlimDto bookSlimDto = bookMapper.bookToBookSlimDto(book);

        assertNotNull(bookSlimDto);
        assertEquals(book.getId(), bookSlimDto.getId());
        assertEquals(book.getTitle(), bookSlimDto.getTitle());
    }

    @Test
    void testBookToBookDto() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book Title");

        BookDto bookDto = bookMapper.bookToBookDto(book);

        assertNotNull(bookDto);
        assertEquals(book.getId(), bookDto.getId());
        assertEquals(book.getTitle(), bookDto.getTitle());
    }

    @Test
    void testBookDtoToBook() {
        BookDto bookDto = new BookDto();
        bookDto.setId(1);
        bookDto.setTitle("Book Title");

        Book book = bookMapper.bookDtoToBook(bookDto);

        assertNotNull(book);
        assertEquals(bookDto.getId(), book.getId());
        assertEquals(bookDto.getTitle(), book.getTitle());
    }

    @Test
    void testBooksToBookDtos() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book Title");

        List<BookDto> bookDtos = bookMapper.booksToBookDtos(Collections.singletonList(book));

        assertNotNull(bookDtos);
        assertEquals(1, bookDtos.size());
        assertEquals(book.getId(), bookDtos.get(0).getId());
        assertEquals(book.getTitle(), bookDtos.get(0).getTitle());
    }

    @Test
    void testBooksToBookSlimDtos() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Book Title");

        Set<BookSlimDto> bookSlimDtos = bookMapper.booksToBookSlimDtos(Collections.singleton(book));

        assertNotNull(bookSlimDtos);
        assertEquals(1, bookSlimDtos.size());
        assertEquals(book.getId(), bookSlimDtos.iterator().next().getId());
        assertEquals(book.getTitle(), bookSlimDtos.iterator().next().getTitle());
    }
}
