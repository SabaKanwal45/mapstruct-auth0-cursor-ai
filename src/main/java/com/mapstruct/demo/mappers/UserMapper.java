package com.mapstruct.demo.mappers;

import com.mapstruct.demo.dtos.UserGetDto;
import com.mapstruct.demo.dtos.UserPostDto;
import com.mapstruct.demo.entities.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BookMapper.class, AuthorMapper.class})
public interface UserMapper {
    
    UserGetDto userToUserGetDto(User user);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    User userPostDtoToUser(UserPostDto userPostDto);
    
    List<UserGetDto> usersToUserGetDtos(List<User> users);
}