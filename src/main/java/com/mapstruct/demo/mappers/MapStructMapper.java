package com.mapstruct.demo.mappers;

import com.mapstruct.demo.dtos.*;
import com.mapstruct.demo.entities.Author;
import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.entities.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MapStructMapper {
    // Existing book mappings
    BookSlimDto bookToBookSlimDto(Book book);
    
    BookDto bookToBookDto(Book book);
    
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "users", ignore = true)
    Book bookDtoToBook(BookDto bookDto);

    // Existing author mappings
    AuthorDto authorToAuthorDto(Author author);
    
    @Mapping(target = "books", ignore = true)
    Author authorDtoToAuthor(AuthorDto authorDto);

    // Existing user mappings
    UserGetDto userToUserGetDto(User user);
    
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "id", ignore = true)
    User userPostDtoToUser(UserPostDto userPostDto);

    // New methods for collections
    List<BookDto> booksToBookDtos(List<Book> books);

    // Update methods
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "users", ignore = true)
    void updateBookFromDto(BookDto bookDto, @MappingTarget Book book);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    void updateAuthorFromDto(AuthorDto authorDto, @MappingTarget Author author);

    @AfterMapping
    default void linkBookAuthors(@MappingTarget Book book) {
        if (book.getAuthors() != null) {
            book.getAuthors().forEach(author -> author.getBooks().add(book));
        }
    }
}