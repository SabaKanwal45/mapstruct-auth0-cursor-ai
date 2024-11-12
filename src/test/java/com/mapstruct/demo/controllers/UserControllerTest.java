package com.mapstruct.demo.controllers;

import com.mapstruct.demo.dtos.BookDto;
import com.mapstruct.demo.dtos.BookSlimDto;
import com.mapstruct.demo.dtos.UserGetDto;
import com.mapstruct.demo.dtos.UserPostDto;
import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.entities.User;
import com.mapstruct.demo.exception.BadRequestException;
import com.mapstruct.demo.mappers.BookMapper;
import com.mapstruct.demo.mappers.UserMapper;
import com.mapstruct.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private BookMapper bookMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(new User());
        List<UserGetDto> userGetDtos = List.of(new UserGetDto());

        when(userService.getAllUsers()).thenReturn(users);
        when(userMapper.userToUserGetDto(any(User.class))).thenReturn(new UserGetDto());

        ResponseEntity<List<UserGetDto>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userGetDtos.size(), response.getBody().size());
    }

    @Test
    void testGetUserById() {
        User user = new User();
        UserGetDto userGetDto = new UserGetDto();

        when(userService.getUserById(1)).thenReturn(user);
        when(userMapper.userToUserGetDto(user)).thenReturn(userGetDto);

        ResponseEntity<UserGetDto> response = userController.getUserById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userGetDto, response.getBody());
    }

    @Test
    void testCreateUser() {
        UserPostDto userPostDto = new UserPostDto();
        User user = new User();
        User savedUser = new User();
        UserGetDto userGetDto = new UserGetDto();

        when(userMapper.userPostDtoToUser(userPostDto)).thenReturn(user);
        when(userService.createUser(user)).thenReturn(savedUser);
        when(userMapper.userToUserGetDto(savedUser)).thenReturn(userGetDto);

        ResponseEntity<UserGetDto> response = userController.createUser(userPostDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userGetDto, response.getBody());
    }

    @Test
    void testUpdateUser() {
        UserPostDto userPostDto = new UserPostDto();
        User user = new User();
        User updatedUser = new User();
        UserGetDto userGetDto = new UserGetDto();

        when(userMapper.userPostDtoToUser(userPostDto)).thenReturn(user);
        when(userService.updateUser(1, user)).thenReturn(updatedUser);
        when(userMapper.userToUserGetDto(updatedUser)).thenReturn(userGetDto);

        ResponseEntity<UserGetDto> response = userController.updateUser(1, userPostDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userGetDto, response.getBody());
    }

    @Test
    void testDeleteUser() {
        ResponseEntity<Void> response = userController.deleteUser(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testAssignBookToUser() throws BadRequestException {
        User user = new User();
        UserGetDto userGetDto = new UserGetDto();

        when(userService.assignBookToUser(1, 1)).thenReturn(user);
        when(userMapper.userToUserGetDto(user)).thenReturn(userGetDto);

        ResponseEntity<UserGetDto> response = userController.assignBookToUser(1, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userGetDto, response.getBody());
    }

    @Test
    void testUnassignBookFromUser() {
        User user = new User();
        UserGetDto userGetDto = new UserGetDto();

        when(userService.unassignBookFromUser(1, 1)).thenReturn(user);
        when(userMapper.userToUserGetDto(user)).thenReturn(userGetDto);

        ResponseEntity<UserGetDto> response = userController.unassignBookFromUser(1, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userGetDto, response.getBody());
    }

    @Test
    void testGetUserBooks() {
        Set<Book> books = Set.of(new Book());
        Set<BookSlimDto> bookSlimDtos = Set.of(new BookSlimDto());

        when(userService.getUserBooks(1)).thenReturn(books);
        when(bookMapper.booksToBookSlimDtos(books)).thenReturn(bookSlimDtos);

        ResponseEntity<Set<BookSlimDto>> response = userController.getUserBooks(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookSlimDtos, response.getBody());
    }

    @Test
    void testGetUserBook() {
        Book book = new Book();
        BookDto bookDto = new BookDto();

        when(userService.getUserBook(1, 1)).thenReturn(book);
        when(bookMapper.bookToBookDto(book)).thenReturn(bookDto);

        ResponseEntity<BookDto> response = userController.getUserBook(1, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookDto, response.getBody());
    }
}
