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
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final BookMapper bookMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserGetDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(
            users.stream()
                .map(userMapper::userToUserGetDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(userMapper.userToUserGetDto(user));
    }

    @PostMapping
    public ResponseEntity<UserGetDto> createUser(@Valid @RequestBody UserPostDto userPostDto) {
        User user = userMapper.userPostDtoToUser(userPostDto);
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToUserGetDto(savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGetDto> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UserPostDto userPostDto) {
        User user = userMapper.userPostDtoToUser(userPostDto);
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(userMapper.userToUserGetDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/books/{bookId}")
    public ResponseEntity<UserGetDto> assignBookToUser(
            @PathVariable Integer userId,
            @PathVariable Integer bookId) throws BadRequestException {
        User user = userService.assignBookToUser(userId, bookId);
        return ResponseEntity.ok(userMapper.userToUserGetDto(user));
    }

    @DeleteMapping("/{userId}/books/{bookId}")
    public ResponseEntity<UserGetDto> unassignBookFromUser(
            @PathVariable Integer userId,
            @PathVariable Integer bookId) {
        User user = userService.unassignBookFromUser(userId, bookId);
        return ResponseEntity.ok(userMapper.userToUserGetDto(user));
    }

    @GetMapping("/{userId}/books")
    public ResponseEntity<Set<BookSlimDto>> getUserBooks(
            @PathVariable Integer userId) {
        Set<Book> books = userService.getUserBooks(userId);
        return ResponseEntity.ok(bookMapper.booksToBookSlimDtos(books));
    }

    @GetMapping("/{userId}/books/{bookId}")
    public ResponseEntity<BookDto> getUserBook(
            @PathVariable Integer userId,
            @PathVariable Integer bookId) {
        Book book = userService.getUserBook(userId, bookId);
        return ResponseEntity.ok(bookMapper.bookToBookDto(book));
    }
}