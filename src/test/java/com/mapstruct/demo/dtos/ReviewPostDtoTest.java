package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewPostDtoTest {

    @Test
    public void testReviewPostDto() {
        // Create an instance of ReviewPostDto
        ReviewPostDto reviewPostDto = new ReviewPostDto();

        // Set values
        reviewPostDto.setRating(5);
        reviewPostDto.setReviewText("Great book!");

        // Assert values
        assertEquals(5, reviewPostDto.getRating());
        assertEquals("Great book!", reviewPostDto.getReviewText());
    }
}
