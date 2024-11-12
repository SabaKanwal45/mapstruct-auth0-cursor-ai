package com.mapstruct.demo.controllers;

import com.mapstruct.demo.dtos.AuthorAllDto;
import com.mapstruct.demo.dtos.AuthorDto;
import com.mapstruct.demo.dtos.BookSlimDto;
import com.mapstruct.demo.entities.Author;
import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.mappers.AuthorMapper;
import com.mapstruct.demo.mappers.BookMapper;
import com.mapstruct.demo.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private AuthorMapper authorMapper;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAuthors() {
        Author author1 = new Author();
        Author author2 = new Author();
        List<Author> authors = Arrays.asList(author1, author2);
        AuthorAllDto authorAllDto1 = new AuthorAllDto();
        AuthorAllDto authorAllDto2 = new AuthorAllDto();
        List<AuthorAllDto> authorAllDtos = Arrays.asList(authorAllDto1, authorAllDto2);

        when(authorService.getAllAuthors()).thenReturn(authors);
        when(authorMapper.authorsToAuthorAllDtos(authors)).thenReturn(authorAllDtos);

        ResponseEntity<List<AuthorAllDto>> response = authorController.getAllAuthors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authorAllDtos, response.getBody());
    }

    @Test
    void testGetAuthorById() {
        Integer authorId = 1;
        Author author = new Author();
        AuthorDto authorDto = new AuthorDto();

        when(authorService.getAuthorById(authorId)).thenReturn(author);
        when(authorMapper.authorToAuthorDto(author)).thenReturn(authorDto);

        ResponseEntity<AuthorDto> response = authorController.getAuthorById(authorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authorDto, response.getBody());
    }

    @Test
    void testCreateAuthor() {
        AuthorDto authorDto = new AuthorDto();
        Author author = new Author();
        Author savedAuthor = new Author();
        AuthorDto savedAuthorDto = new AuthorDto();

        when(authorMapper.authorDtoToAuthor(authorDto)).thenReturn(author);
        when(authorService.createAuthor(author)).thenReturn(savedAuthor);
        when(authorMapper.authorToAuthorDto(savedAuthor)).thenReturn(savedAuthorDto);

        ResponseEntity<AuthorDto> response = authorController.createAuthor(authorDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedAuthorDto, response.getBody());
    }

    @Test
    void testUpdateAuthor() {
        Integer authorId = 1;
        AuthorDto authorDto = new AuthorDto();
        Author author = new Author();
        Author updatedAuthor = new Author();
        AuthorDto updatedAuthorDto = new AuthorDto();

        when(authorMapper.authorDtoToAuthor(authorDto)).thenReturn(author);
        when(authorService.updateAuthor(authorId, author)).thenReturn(updatedAuthor);
        when(authorMapper.authorToAuthorDto(updatedAuthor)).thenReturn(updatedAuthorDto);

        ResponseEntity<AuthorDto> response = authorController.updateAuthor(authorId, authorDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedAuthorDto, response.getBody());
    }

    @Test
    void testDeleteAuthor() {
        Integer authorId = 1;

        ResponseEntity<Void> response = authorController.deleteAuthor(authorId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetAuthorBooks() {
        Integer authorId = 1;
        Book book1 = new Book();
        Book book2 = new Book();
        Set<Book> books = new HashSet<>(Arrays.asList(book1, book2));
        BookSlimDto bookSlimDto1 = new BookSlimDto();
        BookSlimDto bookSlimDto2 = new BookSlimDto();
        Set<BookSlimDto> bookSlimDtos = new HashSet<>(Arrays.asList(bookSlimDto1, bookSlimDto2));

        when(authorService.getAuthorBooks(authorId)).thenReturn(books);
        when(bookMapper.booksToBookSlimDtos(books)).thenReturn(bookSlimDtos);

        ResponseEntity<Set<BookSlimDto>> response = authorController.getAuthorBooks(authorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookSlimDtos, response.getBody());
    }

    @Test
    void testAddBookToAuthor() {
        Integer authorId = 1;
        Integer bookId = 1;
        Author author = new Author();
        AuthorDto authorDto = new AuthorDto();

        when(authorService.addBookToAuthor(authorId, bookId)).thenReturn(author);
        when(authorMapper.authorToAuthorDto(author)).thenReturn(authorDto);

        ResponseEntity<AuthorDto> response = authorController.addBookToAuthor(authorId, bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authorDto, response.getBody());
    }

    @Test
    void testRemoveBookFromAuthor() {
        Integer authorId = 1;
        Integer bookId = 1;
        Author author = new Author();
        AuthorDto authorDto = new AuthorDto();

        when(authorService.removeBookFromAuthor(authorId, bookId)).thenReturn(author);
        when(authorMapper.authorToAuthorDto(author)).thenReturn(authorDto);

        ResponseEntity<AuthorDto> response = authorController.removeBookFromAuthor(authorId, bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(authorDto, response.getBody());
    }
}
