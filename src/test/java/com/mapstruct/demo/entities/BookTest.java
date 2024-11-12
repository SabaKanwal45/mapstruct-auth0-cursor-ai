package com.mapstruct.demo.entities;

import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testBook() {
        // Create an instance of Book
        Book book = new Book();

        // Set values
        book.setId(1);
        book.setTitle("Test Book");
        Date releaseDate = new Date();
        book.setReleaseDate(releaseDate);
        Set<Author> authors = new HashSet<>();
        book.setAuthors(authors);
        Set<User> users = new HashSet<>();
        book.setUsers(users);

        // Assert values
        assertEquals(1, book.getId());
        assertEquals("Test Book", book.getTitle());
        assertEquals(releaseDate, book.getReleaseDate());
        assertEquals(authors, book.getAuthors());
        assertEquals(users, book.getUsers());
    }
}
