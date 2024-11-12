package com.mapstruct.demo.service;

import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.exception.ResourceNotFoundException;
import com.mapstruct.demo.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book();
        Book book2 = new Book();
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetBookById() {
        Book book = new Book();
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1);
        assertEquals(book, result);
    }

    @Test
    public void testGetBookByIdNotFound() {
        when(bookRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.getBookById(1));
    }

    @Test
    public void testCreateBook() {
        Book book = new Book();
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.createBook(book);
        assertEquals(book, result);
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book();
        when(bookRepository.existsById(1)).thenReturn(true);
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.updateBook(1, book);
        assertEquals(book, result);
    }

    @Test
    public void testUpdateBookNotFound() {
        Book book = new Book();
        when(bookRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> bookService.updateBook(1, book));
    }

    @Test
    public void testDeleteBook() {
        when(bookRepository.existsById(1)).thenReturn(true);

        bookService.deleteBook(1);

        verify(bookRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteBookNotFound() {
        when(bookRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> bookService.deleteBook(1));
    }
}
