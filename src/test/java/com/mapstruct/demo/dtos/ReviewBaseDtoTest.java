package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewBaseDtoTest {

    @Test
    public void testReviewBaseDto() {
        // Create an instance of ReviewBaseDto
        ReviewBaseDto reviewBaseDto = new ReviewBaseDto();

        // Set values
        reviewBaseDto.setId(1);
        reviewBaseDto.setRating(5);
        reviewBaseDto.setReviewText("Great book!");
        LocalDateTime now = LocalDateTime.now();
        reviewBaseDto.setCreatedAt(now);

        // Assert values
        assertEquals(1, reviewBaseDto.getId());
        assertEquals(5, reviewBaseDto.getRating());
        assertEquals("Great book!", reviewBaseDto.getReviewText());
        assertEquals(now, reviewBaseDto.getCreatedAt());
    }
}
