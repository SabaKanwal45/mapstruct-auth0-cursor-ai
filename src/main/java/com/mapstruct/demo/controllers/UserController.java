package com.mapstruct.demo.controllers;

import com.mapstruct.demo.dtos.UserGetDto;
import com.mapstruct.demo.dtos.UserPostDto;
import com.mapstruct.demo.entities.User;
import com.mapstruct.demo.mappers.MapStructMapper;
import com.mapstruct.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final MapStructMapper mapstructMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserGetDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users.stream()
                .map(mapstructMapper::userToUserGetDto)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> getUserById(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(mapstructMapper.userToUserGetDto(user));
    }

    @PostMapping
    public ResponseEntity<UserGetDto> createUser(@Valid @RequestBody UserPostDto userPostDto) {
        User user = mapstructMapper.userPostDtoToUser(userPostDto);
        User savedUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapstructMapper.userToUserGetDto(savedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserGetDto> updateUser(
            @PathVariable Integer id,
            @Valid @RequestBody UserPostDto userPostDto) {
        User user = mapstructMapper.userPostDtoToUser(userPostDto);
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(mapstructMapper.userToUserGetDto(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}