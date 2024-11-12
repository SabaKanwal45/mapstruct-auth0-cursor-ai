package com.mapstruct.demo.service;

import com.mapstruct.demo.entities.Author;
import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.exception.ResourceNotFoundException;
import com.mapstruct.demo.repositories.AuthorRepository;
import com.mapstruct.demo.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAuthors() {
        Author author1 = new Author();
        Author author2 = new Author();
        List<Author> authors = Arrays.asList(author1, author2);
        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAllAuthors();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetAuthorById() {
        Author author = new Author();
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        Author result = authorService.getAuthorById(1);
        assertEquals(author, result);
    }

    @Test
    public void testGetAuthorByIdNotFound() {
        when(authorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> authorService.getAuthorById(1));
    }

    @Test
    public void testCreateAuthor() {
        Author author = new Author();
        when(authorRepository.save(author)).thenReturn(author);

        Author result = authorService.createAuthor(author);
        assertEquals(author, result);
    }

    @Test
    public void testUpdateAuthor() {
        Author author = new Author();
        when(authorRepository.existsById(1)).thenReturn(true);
        when(authorRepository.save(author)).thenReturn(author);

        Author result = authorService.updateAuthor(1, author);
        assertEquals(author, result);
    }

    @Test
    public void testUpdateAuthorNotFound() {
        Author author = new Author();
        when(authorRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> authorService.updateAuthor(1, author));
    }

    @Test
    public void testDeleteAuthor() {
        when(authorRepository.existsById(1)).thenReturn(true);

        authorService.deleteAuthor(1);

        verify(authorRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteAuthorNotFound() {
        when(authorRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> authorService.deleteAuthor(1));
    }

    @Test
    public void testAddBookToAuthor() {
        Author author = new Author();
        Book book = new Book();
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(authorRepository.save(author)).thenReturn(author);

        Author result = authorService.addBookToAuthor(1, 1);
        assertEquals(author, result);
        assertTrue(author.getBooks().contains(book));
        assertTrue(book.getAuthors().contains(author));
    }

    @Test
    public void testRemoveBookFromAuthor() {
        Author author = new Author();
        Book book = new Book();
        author.setBooks(new HashSet<>(Arrays.asList(book)));
        book.setAuthors(new HashSet<>(Arrays.asList(author)));
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(authorRepository.save(author)).thenReturn(author);

        Author result = authorService.removeBookFromAuthor(1, 1);
        assertEquals(author, result);
        assertFalse(author.getBooks().contains(book));
        assertFalse(book.getAuthors().contains(author));
    }

    @Test
    public void testGetAuthorBooks() {
        Author author = new Author();
        Set<Book> books = new HashSet<>(Arrays.asList(new Book(), new Book()));
        author.setBooks(books);
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));

        Set<Book> result = authorService.getAuthorBooks(1);
        assertEquals(2, result.size());
    }
}
