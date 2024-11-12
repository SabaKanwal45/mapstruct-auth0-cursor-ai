package com.mapstruct.demo.entities;

import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class AuthorTest {

    @Test
    public void testAuthor() {
        // Create an instance of Author
        Author author = new Author();

        // Set values
        author.setId(1);
        author.setName("John");
        author.setSurname("Doe");
        Date birthDate = new Date();
        author.setBirthDate(birthDate);
        Set<Book> books = new HashSet<>();
        author.setBooks(books);

        // Assert values
        assertEquals(1, author.getId());
        assertEquals("John", author.getName());
        assertEquals("Doe", author.getSurname());
        assertEquals(birthDate, author.getBirthDate());
        assertEquals(books, author.getBooks());
    }
}
