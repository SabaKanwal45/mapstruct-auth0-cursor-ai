package com.mapstruct.demo.mappers;

import com.mapstruct.demo.dtos.AuthorAllDto;
import com.mapstruct.demo.dtos.AuthorDto;
import com.mapstruct.demo.entities.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class AuthorMapperTest {

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private AuthorMapperImpl authorMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthorToAuthorDto() {
        Author author = new Author();
        author.setId(1);
        author.setName("Author Name");

        AuthorDto authorDto = authorMapper.authorToAuthorDto(author);

        assertNotNull(authorDto);
        assertEquals(author.getId(), authorDto.getId());
        assertEquals(author.getName(), authorDto.getName());
    }

    @Test
    void testAuthorToAuthorAllDto() {
        Author author = new Author();
        author.setId(1);
        author.setName("Author Name");

        when(bookMapper.booksToBookSlimDtos(Collections.emptySet())).thenReturn(Collections.emptySet());

        AuthorAllDto authorAllDto = authorMapper.authorToAuthorAllDto(author);

        assertNotNull(authorAllDto);
        assertEquals(author.getId(), authorAllDto.getId());
        assertEquals(author.getName(), authorAllDto.getName());
    }

    @Test
    void testAuthorsToAuthorAllDtos() {
        Author author = new Author();
        author.setId(1);
        author.setName("Author Name");

        when(bookMapper.booksToBookSlimDtos(Collections.emptySet())).thenReturn(Collections.emptySet());

        List<AuthorAllDto> authorAllDtos = authorMapper.authorsToAuthorAllDtos(Collections.singletonList(author));

        assertNotNull(authorAllDtos);
        assertEquals(1, authorAllDtos.size());
        assertEquals(author.getId(), authorAllDtos.get(0).getId());
        assertEquals(author.getName(), authorAllDtos.get(0).getName());
    }

    @Test
    void testAuthorDtoToAuthor() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(1);
        authorDto.setName("Author Name");

        Author author = authorMapper.authorDtoToAuthor(authorDto);

        assertNotNull(author);
        assertEquals(authorDto.getId(), author.getId());
        assertEquals(authorDto.getName(), author.getName());
    }
}
