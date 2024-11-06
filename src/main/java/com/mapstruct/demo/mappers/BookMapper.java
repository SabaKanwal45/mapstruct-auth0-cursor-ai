package com.mapstruct.demo.mappers;

import com.mapstruct.demo.dtos.BookDto;
import com.mapstruct.demo.dtos.BookSlimDto;
import com.mapstruct.demo.entities.Book;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface BookMapper {
    
    BookSlimDto bookToBookSlimDto(Book book);
    
    BookDto bookToBookDto(Book book);
    
    @Mapping(target = "authors", ignore = true)
    Book bookDtoToBook(BookDto bookDto);
    
    List<BookDto> booksToBookDtos(List<Book> books);
    
    Set<BookSlimDto> booksToBookSlimDtos(Set<Book> books);
}