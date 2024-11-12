package com.mapstruct.demo.dtos;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewWithUserDtoTest {

    @Test
    public void testReviewWithUserDto() {
        // Create an instance of ReviewWithUserDto
        ReviewWithUserDto reviewWithUserDto = new ReviewWithUserDto();

        // Set values
        reviewWithUserDto.setId(1);
        reviewWithUserDto.setRating(5);
        reviewWithUserDto.setReviewText("Great book!");
        LocalDateTime now = LocalDateTime.now();
        reviewWithUserDto.setCreatedAt(now);
        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setId(1);
        userGetDto.setName("Test User");
        reviewWithUserDto.setUser(userGetDto);

        // Assert values
        assertEquals(1, reviewWithUserDto.getId());
        assertEquals(5, reviewWithUserDto.getRating());
        assertEquals("Great book!", reviewWithUserDto.getReviewText());
        assertEquals(now, reviewWithUserDto.getCreatedAt());
        assertEquals(userGetDto, reviewWithUserDto.getUser());
    }
}
