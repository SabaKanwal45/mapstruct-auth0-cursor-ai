package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewGetDtoTest {

    @Test
    public void testReviewGetDto() {
        // Create an instance of ReviewGetDto
        ReviewGetDto reviewGetDto = new ReviewGetDto();

        // Set values
        reviewGetDto.setId(1);
        reviewGetDto.setRating(5);
        reviewGetDto.setReviewText("Great book!");
        LocalDateTime now = LocalDateTime.now();
        reviewGetDto.setCreatedAt(now);
        BookSlimDto bookSlimDto = new BookSlimDto();
        bookSlimDto.setId(1);
        bookSlimDto.setTitle("Test Book");
        reviewGetDto.setBook(bookSlimDto);
        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setId(1);
        userGetDto.setName("Test User");
        reviewGetDto.setUser(userGetDto);

        // Assert values
        assertEquals(1, reviewGetDto.getId());
        assertEquals(5, reviewGetDto.getRating());
        assertEquals("Great book!", reviewGetDto.getReviewText());
        assertEquals(now, reviewGetDto.getCreatedAt());
        assertEquals(bookSlimDto, reviewGetDto.getBook());
        assertEquals(userGetDto, reviewGetDto.getUser());
    }
}
