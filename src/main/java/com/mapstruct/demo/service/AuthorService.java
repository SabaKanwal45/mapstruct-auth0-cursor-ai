package com.mapstruct.demo.service;

import com.mapstruct.demo.entities.Author;
import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.exception.ResourceNotFoundException;
import com.mapstruct.demo.repositories.AuthorRepository;
import com.mapstruct.demo.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    public Author createAuthor(Author author) {
        author.setId(0); // Ensure new author creation
        return authorRepository.save(author);
    }

    public Author updateAuthor(Integer id, Author author) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
        author.setId(id);
        return authorRepository.save(author);
    }

    public void deleteAuthor(Integer id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }

    @Transactional
    public Author addBookToAuthor(Integer authorId, Integer bookId) {
        Author author = getAuthorById(authorId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        
        author.getBooks().add(book);
        book.getAuthors().add(author);
        
        return authorRepository.save(author);
    }

    @Transactional
    public Author removeBookFromAuthor(Integer authorId, Integer bookId) {
        Author author = getAuthorById(authorId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));
        
        author.getBooks().remove(book);
        book.getAuthors().remove(author);
        
        return authorRepository.save(author);
    }
}