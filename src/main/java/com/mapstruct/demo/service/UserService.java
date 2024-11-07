package com.mapstruct.demo.service;

import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.entities.User;
import com.mapstruct.demo.exception.BadRequestException;
import com.mapstruct.demo.exception.ResourceNotFoundException;
import com.mapstruct.demo.repositories.BookRepository;
import com.mapstruct.demo.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User createUser(User user) {
        user.setId(0); // Ensure new user creation
        return userRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public User assignBookToUser(Integer userId, Integer bookId) throws BadRequestException {
        User user = getUserById(userId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        if (user.getBooks().contains(book)) {
            System.out.println("Throwing BadRequestException: Book is already assigned"); // Add logging
            throw new BadRequestException("Book is already assigned to this user");
        }

        user.getBooks().add(book);
        return userRepository.save(user);
    }

    @Transactional
    public User unassignBookFromUser(Integer userId, Integer bookId) {
        User user = getUserById(userId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        if (!user.getBooks().contains(book)) {
            throw new ResourceNotFoundException("Book is not assigned to this user");
        }

        user.getBooks().remove(book);
        return userRepository.save(user);
    }

    public Set<Book> getUserBooks(Integer userId) {
        User user = getUserById(userId);
        return user.getBooks();
    }

    public Book getUserBook(Integer userId, Integer bookId) {
        User user = getUserById(userId);
        return user.getBooks().stream()
                .filter(book -> book.getId() == bookId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Book not found for user with id: " + userId));
    }
}