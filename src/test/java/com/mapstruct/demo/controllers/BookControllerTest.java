package com.mapstruct.demo.controllers;

import com.mapstruct.demo.dtos.BookDto;
import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.mappers.BookMapper;
import com.mapstruct.demo.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {
        Book book1 = new Book();
        Book book2 = new Book();
        List<Book> books = Arrays.asList(book1, book2);
        BookDto bookDto1 = new BookDto();
        BookDto bookDto2 = new BookDto();
        List<BookDto> bookDtos = Arrays.asList(bookDto1, bookDto2);

        when(bookService.getAllBooks()).thenReturn(books);
        when(bookMapper.booksToBookDtos(books)).thenReturn(bookDtos);

        ResponseEntity<List<BookDto>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookDtos, response.getBody());
    }

    @Test
    void testGetBookById() {
        Integer bookId = 1;
        Book book = new Book();
        BookDto bookDto = new BookDto();

        when(bookService.getBookById(bookId)).thenReturn(book);
        when(bookMapper.bookToBookDto(book)).thenReturn(bookDto);

        ResponseEntity<BookDto> response = bookController.getBookById(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookDto, response.getBody());
    }

    @Test
    void testCreateBook() {
        BookDto bookDto = new BookDto();
        Book book = new Book();
        Book savedBook = new Book();
        BookDto savedBookDto = new BookDto();

        when(bookMapper.bookDtoToBook(bookDto)).thenReturn(book);
        when(bookService.createBook(book)).thenReturn(savedBook);
        when(bookMapper.bookToBookDto(savedBook)).thenReturn(savedBookDto);

        ResponseEntity<BookDto> response = bookController.createBook(bookDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedBookDto, response.getBody());
    }

    @Test
    void testUpdateBook() {
        Integer bookId = 1;
        BookDto bookDto = new BookDto();
        Book book = new Book();
        Book updatedBook = new Book();
        BookDto updatedBookDto = new BookDto();

        when(bookMapper.bookDtoToBook(bookDto)).thenReturn(book);
        when(bookService.updateBook(bookId, book)).thenReturn(updatedBook);
        when(bookMapper.bookToBookDto(updatedBook)).thenReturn(updatedBookDto);

        ResponseEntity<BookDto> response = bookController.updateBook(bookId, bookDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBookDto, response.getBody());
    }

    @Test
    void testDeleteBook() {
        Integer bookId = 1;

        ResponseEntity<Void> response = bookController.deleteBook(bookId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
