package com.mapstruct.demo.entities;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUser() {
        // Create an instance of User
        User user = new User();

        // Set values
        user.setId(1);
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setName("John");
        user.setSurname("Doe");
        Set<Book> books = new HashSet<>();
        user.setBooks(books);

        // Assert values
        assertEquals(1, user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getSurname());
        assertEquals(books, user.getBooks());
    }
}
