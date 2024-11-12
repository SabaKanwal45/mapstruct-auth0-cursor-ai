package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewMetadataDtoTest {

    @Test
    public void testReviewMetadataDto() {
        // Create an instance of ReviewMetadataDto
        ReviewMetadataDto reviewMetadataDto = new ReviewMetadataDto();

        // Set values
        reviewMetadataDto.setId(1);
        reviewMetadataDto.setRating(5);
        reviewMetadataDto.setReviewText("Great book!");
        LocalDateTime now = LocalDateTime.now();
        reviewMetadataDto.setCreatedAt(now);

        // Assert values
        assertEquals(1, reviewMetadataDto.getId());
        assertEquals(5, reviewMetadataDto.getRating());
        assertEquals("Great book!", reviewMetadataDto.getReviewText());
        assertEquals(now, reviewMetadataDto.getCreatedAt());
    }
}
