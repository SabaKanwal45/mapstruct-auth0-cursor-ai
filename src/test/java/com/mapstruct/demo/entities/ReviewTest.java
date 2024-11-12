package com.mapstruct.demo.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {

    @Test
    public void testReview() {
        // Create an instance of Review
        Review review = new Review();

        // Set values
        review.setId(1);
        Book book = new Book();
        book.setId(1);
        book.setTitle("Test Book");
        review.setBook(book);
        User user = new User();
        user.setId(1);
        user.setName("Test User");
        review.setUser(user);
        review.setRating(5);
        review.setReviewText("Great book!");
        LocalDateTime now = LocalDateTime.now();
        review.setCreatedAt(now);

        // Assert values
        assertEquals(1, review.getId());
        assertEquals(book, review.getBook());
        assertEquals(user, review.getUser());
        assertEquals(5, review.getRating());
        assertEquals("Great book!", review.getReviewText());
        assertEquals(now, review.getCreatedAt());
    }
}
