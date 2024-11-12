package com.mapstruct.demo.mappers;

import com.mapstruct.demo.dtos.*;
import com.mapstruct.demo.entities.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class ReviewMapperTest {

    @Mock
    private BookMapper bookMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private ReviewMapperImpl reviewMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReviewPostDtoToReview() {
        ReviewPostDto reviewPostDto = new ReviewPostDto();
        reviewPostDto.setReviewText("Review Content");

        Review review = reviewMapper.reviewPostDtoToReview(reviewPostDto);

        assertNotNull(review);
        assertEquals(reviewPostDto.getReviewText(), review.getReviewText());
    }

    @Test
    void testReviewToMetadataDto() {
        Review review = new Review();
        review.setId(1);
        review.setReviewText("Review Content");

        ReviewMetadataDto reviewMetadataDto = reviewMapper.reviewToMetadataDto(review);

        assertNotNull(reviewMetadataDto);
        assertEquals(review.getId(), reviewMetadataDto.getId());
        assertEquals(review.getReviewText(), reviewMetadataDto.getReviewText());
    }

    @Test
    void testReviewToReviewWithBookDto() {
        Review review = new Review();
        review.setId(1);
        review.setReviewText("Review Content");

        when(bookMapper.bookToBookSlimDto(null)).thenReturn(null);

        ReviewWithBookDto reviewWithBookDto = reviewMapper.reviewToReviewWithBookDto(review);

        assertNotNull(reviewWithBookDto);
        assertEquals(review.getId(), reviewWithBookDto.getId());
        assertEquals(review.getReviewText(), reviewWithBookDto.getReviewText());
    }

    @Test
    void testReviewToReviewWithUserDto() {
        Review review = new Review();
        review.setId(1);
        review.setReviewText("Review Content");

        when(userMapper.userToUserGetDto(null)).thenReturn(null);

        ReviewWithUserDto reviewWithUserDto = reviewMapper.reviewToReviewWithUserDto(review);

        assertNotNull(reviewWithUserDto);
        assertEquals(review.getId(), reviewWithUserDto.getId());
        assertEquals(review.getReviewText(), reviewWithUserDto.getReviewText());
    }

    @Test
    void testReviewToReviewGetDto() {
        Review review = new Review();
        review.setId(1);
        review.setReviewText("Review Content");

        when(bookMapper.bookToBookSlimDto(null)).thenReturn(null);
        when(userMapper.userToUserGetDto(null)).thenReturn(null);

        ReviewGetDto reviewGetDto = reviewMapper.reviewToReviewGetDto(review);

        assertNotNull(reviewGetDto);
        assertEquals(review.getId(), reviewGetDto.getId());
        assertEquals(review.getReviewText(), reviewGetDto.getReviewText());
    }

    @Test
    void testReviewsToMetadataDtos() {
        Review review = new Review();
        review.setId(1);
        review.setReviewText("Review Content");

        List<ReviewMetadataDto> reviewMetadataDtos = reviewMapper.reviewsToMetadataDtos(Collections.singletonList(review));

        assertNotNull(reviewMetadataDtos);
        assertEquals(1, reviewMetadataDtos.size());
        assertEquals(review.getId(), reviewMetadataDtos.get(0).getId());
        assertEquals(review.getReviewText(), reviewMetadataDtos.get(0).getReviewText());
    }

    @Test
    void testReviewsToReviewWithBookDtos() {
        Review review = new Review();
        review.setId(1);
        review.setReviewText("Review Content");

        when(bookMapper.bookToBookSlimDto(null)).thenReturn(null);

        List<ReviewWithBookDto> reviewWithBookDtos = reviewMapper.reviewsToReviewWithBookDtos(Collections.singletonList(review));

        assertNotNull(reviewWithBookDtos);
        assertEquals(1, reviewWithBookDtos.size());
        assertEquals(review.getId(), reviewWithBookDtos.get(0).getId());
        assertEquals(review.getReviewText(), reviewWithBookDtos.get(0).getReviewText());
    }

    @Test
    void testReviewsToReviewWithUserDtos() {
        Review review = new Review();
        review.setId(1);
        review.setReviewText("Review Content");

        when(userMapper.userToUserGetDto(null)).thenReturn(null);

        List<ReviewWithUserDto> reviewWithUserDtos = reviewMapper.reviewsToReviewWithUserDtos(Collections.singletonList(review));

        assertNotNull(reviewWithUserDtos);
        assertEquals(1, reviewWithUserDtos.size());
        assertEquals(review.getId(), reviewWithUserDtos.get(0).getId());
        assertEquals(review.getReviewText(), reviewWithUserDtos.get(0).getReviewText());
    }
}
