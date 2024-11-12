package com.mapstruct.demo.service;

import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.entities.User;
import com.mapstruct.demo.exception.BadRequestException;
import com.mapstruct.demo.exception.ResourceNotFoundException;
import com.mapstruct.demo.repositories.BookRepository;
import com.mapstruct.demo.repositories.UserRepository;
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

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1);
        assertEquals(user, result);
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1));
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);
        assertEquals(user, result);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        when(userRepository.existsById(1)).thenReturn(true);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUser(1, user);
        assertEquals(user, result);
    }

    @Test
    public void testUpdateUserNotFound() {
        User user = new User();
        when(userRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(1, user));
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.existsById(1)).thenReturn(true);

        userService.deleteUser(1);

        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteUserNotFound() {
        when(userRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(1));
    }

    @Test
    public void testAssignBookToUser() {
        User user = new User();
        Book book = new Book();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.assignBookToUser(1, 1);
        assertEquals(user, result);
        assertTrue(user.getBooks().contains(book));
    }

    @Test
    public void testAssignBookToUserAlreadyAssigned() {
        User user = new User();
        Book book = new Book();
        user.setBooks(new HashSet<>(Arrays.asList(book)));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        assertThrows(BadRequestException.class, () -> userService.assignBookToUser(1, 1));
    }

    @Test
    public void testUnassignBookFromUser() {
        User user = new User();
        Book book = new Book();
        user.setBooks(new HashSet<>(Arrays.asList(book)));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.unassignBookFromUser(1, 1);
        assertEquals(user, result);
        assertFalse(user.getBooks().contains(book));
    }

    @Test
    public void testUnassignBookFromUserNotAssigned() {
        User user = new User();
        Book book = new Book();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        assertThrows(ResourceNotFoundException.class, () -> userService.unassignBookFromUser(1, 1));
    }

    @Test
    public void testGetUserBooks() {
        User user = new User();
        Set<Book> books = new HashSet<>(Arrays.asList(new Book(), new Book()));
        user.setBooks(books);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Set<Book> result = userService.getUserBooks(1);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetUserBook() {
        User user = new User();
        Book book = new Book();
        book.setId(1);
        user.setBooks(new HashSet<>(Arrays.asList(book)));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Book result = userService.getUserBook(1, 1);
        assertEquals(book, result);
    }

    @Test
    public void testGetUserBookNotFound() {
        User user = new User();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserBook(1, 1));
    }
}
