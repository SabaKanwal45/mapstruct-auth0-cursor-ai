package com.mapstruct.demo.mappers;

import com.mapstruct.demo.dtos.AuthorDto;
import com.mapstruct.demo.dtos.AuthorAllDto;
import com.mapstruct.demo.entities.Author;
import org.mapstruct.*;

import java.util.List;
@Mapper(componentModel = "spring", uses = {BookMapper.class})
public interface AuthorMapper {
    
    AuthorDto authorToAuthorDto(Author author);
    
    AuthorAllDto authorToAuthorAllDto(Author author);
    
    List<AuthorAllDto> authorsToAuthorAllDtos(List<Author> authors);
    
    @Mapping(target = "books", ignore = true)
    Author authorDtoToAuthor(AuthorDto authorDto);
}