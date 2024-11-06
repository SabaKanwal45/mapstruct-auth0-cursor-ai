package com.mapstruct.demo.controllers;

import com.mapstruct.demo.dtos.AuthorDto;
import com.mapstruct.demo.dtos.BookSlimDto;
import com.mapstruct.demo.dtos.AuthorAllDto;
import com.mapstruct.demo.entities.Author;
import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.mappers.AuthorMapper;
import com.mapstruct.demo.mappers.BookMapper;
import com.mapstruct.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorAllDto>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authorMapper.authorsToAuthorAllDtos(authors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Integer id) {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorMapper.authorToAuthorDto(author));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        Author author = authorMapper.authorDtoToAuthor(authorDto);
        Author savedAuthor = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorMapper.authorToAuthorDto(savedAuthor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(
            @PathVariable Integer id,
            @RequestBody AuthorDto authorDto) {
        Author author = authorMapper.authorDtoToAuthor(authorDto);
        Author updatedAuthor = authorService.updateAuthor(id, author);
        return ResponseEntity.ok(authorMapper.authorToAuthorDto(updatedAuthor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    
    @GetMapping("/{authorId}/books")
    public ResponseEntity<Set<BookSlimDto>> getAuthorBooks(@PathVariable(name = "authorId") Integer authorId) {
        Set<Book> books = authorService.getAuthorBooks(authorId);
        return ResponseEntity.ok(bookMapper.booksToBookSlimDtos(books));
    }

    @PostMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<AuthorDto> addBookToAuthor(
            @PathVariable(name = "authorId") Integer authorId,
            @PathVariable(name = "bookId") Integer bookId) {
        Author author = authorService.addBookToAuthor(authorId, bookId);
        return ResponseEntity.ok(authorMapper.authorToAuthorDto(author));
    }

    @DeleteMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<AuthorDto> removeBookFromAuthor(
            @PathVariable(name = "authorId") Integer authorId,
            @PathVariable(name = "bookId") Integer bookId) {
        Author author = authorService.removeBookFromAuthor(authorId, bookId);
        return ResponseEntity.ok(authorMapper.authorToAuthorDto(author));
    }

}