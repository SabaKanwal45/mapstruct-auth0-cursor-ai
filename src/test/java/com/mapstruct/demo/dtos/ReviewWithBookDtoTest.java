package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewWithBookDtoTest {

    @Test
    public void testReviewWithBookDto() {
        // Create an instance of ReviewWithBookDto
        ReviewWithBookDto reviewWithBookDto = new ReviewWithBookDto();

        // Set values
        reviewWithBookDto.setId(1);
        reviewWithBookDto.setRating(5);
        reviewWithBookDto.setReviewText("Great book!");
        LocalDateTime now = LocalDateTime.now();
        reviewWithBookDto.setCreatedAt(now);
        BookSlimDto bookSlimDto = new BookSlimDto();
        bookSlimDto.setId(1);
        bookSlimDto.setTitle("Test Book");
        reviewWithBookDto.setBook(bookSlimDto);

        // Assert values
        assertEquals(1, reviewWithBookDto.getId());
        assertEquals(5, reviewWithBookDto.getRating());
        assertEquals("Great book!", reviewWithBookDto.getReviewText());
        assertEquals(now, reviewWithBookDto.getCreatedAt());
        assertEquals(bookSlimDto, reviewWithBookDto.getBook());
    }
}
