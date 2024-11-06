package com.mapstruct.demo.controllers;

import com.mapstruct.demo.dtos.BookDto;
import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.mappers.BookMapper;
import com.mapstruct.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

        private final BookMapper bookMapper;

        private final BookService bookService;

        @GetMapping
        public ResponseEntity<List<BookDto>> getAllBooks() {
                List<Book> books = bookService.getAllBooks();
                return ResponseEntity.ok(bookMapper.booksToBookDtos(books));
        }

        @GetMapping("/{id}")
        public ResponseEntity<BookDto> getBookById(@PathVariable Integer id) {
                Book book = bookService.getBookById(id);
                return ResponseEntity.ok(bookMapper.bookToBookDto(book));
        }

        @PostMapping
        public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
                Book book = bookMapper.bookDtoToBook(bookDto);
                Book savedBook = bookService.createBook(book);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(bookMapper.bookToBookDto(savedBook));
        }

        @PutMapping("/{id}")
        public ResponseEntity<BookDto> updateBook(
                        @PathVariable Integer id,
                        @RequestBody BookDto bookDto) {
                Book book = bookMapper.bookDtoToBook(bookDto);
                Book updatedBook = bookService.updateBook(id, book);
                return ResponseEntity.ok(bookMapper.bookToBookDto(updatedBook));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
                bookService.deleteBook(id);
                return ResponseEntity.noContent().build();
        }
}