package com.mapstruct.demo.controllers;

import com.mapstruct.demo.dtos.AuthorDto;
import com.mapstruct.demo.dtos.AuthorAllDto;
import com.mapstruct.demo.entities.Author;
import com.mapstruct.demo.mappers.MapStructMapper;
import com.mapstruct.demo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final MapStructMapper mapstructMapper;
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorAllDto>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(mapstructMapper.authorsToAuthorAllDtos(authors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Integer id) {
        Author author = authorService.getAuthorById(id);
        return ResponseEntity.ok(mapstructMapper.authorToAuthorDto(author));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        Author author = mapstructMapper.authorDtoToAuthor(authorDto);
        Author savedAuthor = authorService.createAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapstructMapper.authorToAuthorDto(savedAuthor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(
            @PathVariable Integer id,
            @RequestBody AuthorDto authorDto) {
        Author author = mapstructMapper.authorDtoToAuthor(authorDto);
        Author updatedAuthor = authorService.updateAuthor(id, author);
        return ResponseEntity.ok(mapstructMapper.authorToAuthorDto(updatedAuthor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<AuthorDto> addBookToAuthor(
            @PathVariable Integer authorId,
            @PathVariable Integer bookId) {
        Author author = authorService.addBookToAuthor(authorId, bookId);
        return ResponseEntity.ok(mapstructMapper.authorToAuthorDto(author));
    }

    @DeleteMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<AuthorDto> removeBookFromAuthor(
            @PathVariable Integer authorId,
            @PathVariable Integer bookId) {
        Author author = authorService.removeBookFromAuthor(authorId, bookId);
        return ResponseEntity.ok(mapstructMapper.authorToAuthorDto(author));
    }
}