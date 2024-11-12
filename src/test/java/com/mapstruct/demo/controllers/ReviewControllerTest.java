package com.mapstruct.demo.controllers;

import com.mapstruct.demo.dtos.*;
import com.mapstruct.demo.entities.Review;
import com.mapstruct.demo.mappers.ReviewMapper;
import com.mapstruct.demo.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllReviews() {
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        ReviewMetadataDto reviewMetadataDto1 = new ReviewMetadataDto();
        ReviewMetadataDto reviewMetadataDto2 = new ReviewMetadataDto();
        List<ReviewMetadataDto> reviewMetadataDtos = Arrays.asList(reviewMetadataDto1, reviewMetadataDto2);

        when(reviewService.getAllReviews()).thenReturn(reviews);
        when(reviewMapper.reviewsToMetadataDtos(reviews)).thenReturn(reviewMetadataDtos);

        ResponseEntity<List<ReviewMetadataDto>> response = reviewController.getAllReviews();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviewMetadataDtos, response.getBody());
    }

    @Test
    void testGetReviewById() {
        Integer reviewId = 1;
        Review review = new Review();
        ReviewGetDto reviewGetDto = new ReviewGetDto();

        when(reviewService.getReviewById(reviewId)).thenReturn(review);
        when(reviewMapper.reviewToReviewGetDto(review)).thenReturn(reviewGetDto);

        ResponseEntity<ReviewGetDto> response = reviewController.getReviewById(reviewId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviewGetDto, response.getBody());
    }

    @Test
    void testGetReviewsByBook() {
        Integer bookId = 1;
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        ReviewWithUserDto reviewWithUserDto1 = new ReviewWithUserDto();
        ReviewWithUserDto reviewWithUserDto2 = new ReviewWithUserDto();
        List<ReviewWithUserDto> reviewWithUserDtos = Arrays.asList(reviewWithUserDto1, reviewWithUserDto2);

        when(reviewService.getReviewsByBookId(bookId)).thenReturn(reviews);
        when(reviewMapper.reviewsToReviewWithUserDtos(reviews)).thenReturn(reviewWithUserDtos);

        ResponseEntity<List<ReviewWithUserDto>> response = reviewController.getReviewsByBook(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviewWithUserDtos, response.getBody());
    }

    @Test
    void testGetReviewsByUser() {
        Integer userId = 1;
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        ReviewWithBookDto reviewWithBookDto1 = new ReviewWithBookDto();
        ReviewWithBookDto reviewWithBookDto2 = new ReviewWithBookDto();
        List<ReviewWithBookDto> reviewWithBookDtos = Arrays.asList(reviewWithBookDto1, reviewWithBookDto2);

        when(reviewService.getReviewsByUserId(userId)).thenReturn(reviews);
        when(reviewMapper.reviewsToReviewWithBookDtos(reviews)).thenReturn(reviewWithBookDtos);

        ResponseEntity<List<ReviewWithBookDto>> response = reviewController.getReviewsByUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviewWithBookDtos, response.getBody());
    }

    @Test
    void testCreateReview() {
        Integer userId = 1;
        Integer bookId = 1;
        ReviewPostDto reviewPostDto = new ReviewPostDto();
        Review review = new Review();
        Review savedReview = new Review();
        ReviewMetadataDto reviewMetadataDto = new ReviewMetadataDto();

        when(reviewMapper.reviewPostDtoToReview(reviewPostDto)).thenReturn(review);
        when(reviewService.createReview(userId, bookId, review)).thenReturn(savedReview);
        when(reviewMapper.reviewToMetadataDto(savedReview)).thenReturn(reviewMetadataDto);

        ResponseEntity<ReviewMetadataDto> response = reviewController.createReview(userId, bookId, reviewPostDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(reviewMetadataDto, response.getBody());
    }

    @Test
    void testUpdateReview() {
        Integer userId = 1;
        Integer bookId = 1;
        ReviewPostDto reviewPostDto = new ReviewPostDto();
        Review review = new Review();
        Review updatedReview = new Review();
        ReviewMetadataDto reviewMetadataDto = new ReviewMetadataDto();

        when(reviewMapper.reviewPostDtoToReview(reviewPostDto)).thenReturn(review);
        when(reviewService.updateReview(userId, bookId, review)).thenReturn(updatedReview);
        when(reviewMapper.reviewToMetadataDto(updatedReview)).thenReturn(reviewMetadataDto);

        ResponseEntity<ReviewMetadataDto> response = reviewController.updateReview(userId, bookId, reviewPostDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviewMetadataDto, response.getBody());
    }

    @Test
    void testDeleteReview() {
        Integer reviewId = 1;

        ResponseEntity<Void> response = reviewController.deleteReview(reviewId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetReviewsByBookMetadata() {
        Integer bookId = 1;
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        ReviewMetadataDto reviewMetadataDto1 = new ReviewMetadataDto();
        ReviewMetadataDto reviewMetadataDto2 = new ReviewMetadataDto();
        List<ReviewMetadataDto> reviewMetadataDtos = Arrays.asList(reviewMetadataDto1, reviewMetadataDto2);

        when(reviewService.getReviewsByBookId(bookId)).thenReturn(reviews);
        when(reviewMapper.reviewsToMetadataDtos(reviews)).thenReturn(reviewMetadataDtos);

        ResponseEntity<List<ReviewMetadataDto>> response = reviewController.getReviewsByBookMetadata(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviewMetadataDtos, response.getBody());
    }

    @Test
    void testGetReviewsByUserMetadata() {
        Integer userId = 1;
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        ReviewMetadataDto reviewMetadataDto1 = new ReviewMetadataDto();
        ReviewMetadataDto reviewMetadataDto2 = new ReviewMetadataDto();
        List<ReviewMetadataDto> reviewMetadataDtos = Arrays.asList(reviewMetadataDto1, reviewMetadataDto2);

        when(reviewService.getReviewsByUserId(userId)).thenReturn(reviews);
        when(reviewMapper.reviewsToMetadataDtos(reviews)).thenReturn(reviewMetadataDtos);

        ResponseEntity<List<ReviewMetadataDto>> response = reviewController.getReviewsByUserMetadata(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(reviewMetadataDtos, response.getBody());
    }
}
