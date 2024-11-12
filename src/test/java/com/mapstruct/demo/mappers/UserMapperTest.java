package com.mapstruct.demo.mappers;

import com.mapstruct.demo.dtos.UserGetDto;
import com.mapstruct.demo.dtos.UserPostDto;
import com.mapstruct.demo.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test
    void testUserToUserGetDto() {
        User user = new User();
        user.setId(1);
        user.setName("User Name");

        UserGetDto userGetDto = userMapper.userToUserGetDto(user);

        assertNotNull(userGetDto);
        assertEquals(user.getId(), userGetDto.getId());
        assertEquals(user.getName(), userGetDto.getName());
    }

    @Test
    void testUserPostDtoToUser() {
        UserPostDto userPostDto = new UserPostDto();
        userPostDto.setName("User Name");

        User user = userMapper.userPostDtoToUser(userPostDto);

        assertNotNull(user);
        assertEquals(userPostDto.getName(), user.getName());
    }

    @Test
    void testUsersToUserGetDtos() {
        User user = new User();
        user.setId(1);
        user.setName("User Name");

        List<UserGetDto> userGetDtos = userMapper.usersToUserGetDtos(Collections.singletonList(user));

        assertNotNull(userGetDtos);
        assertEquals(1, userGetDtos.size());
        assertEquals(user.getId(), userGetDtos.get(0).getId());
        assertEquals(user.getName(), userGetDtos.get(0).getName());
    }
}
