package com.mapstruct.demo.service;

import com.mapstruct.demo.entities.Review;
import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.entities.User;
import com.mapstruct.demo.exception.BadRequestException;
import com.mapstruct.demo.exception.ResourceNotFoundException;
import com.mapstruct.demo.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllReviews() {
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewRepository.findAll()).thenReturn(reviews);

        List<Review> result = reviewService.getAllReviews();
        assertEquals(2, result.size());
    }

    @Test
    public void testGetReviewById() {
        Review review = new Review();
        when(reviewRepository.findById(1)).thenReturn(Optional.of(review));

        Review result = reviewService.getReviewById(1);
        assertEquals(review, result);
    }

    @Test
    public void testGetReviewByIdNotFound() {
        when(reviewRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reviewService.getReviewById(1));
    }

    @Test
    public void testGetReviewsByBookId() {
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewRepository.findByBookId(1)).thenReturn(reviews);

        List<Review> result = reviewService.getReviewsByBookId(1);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetReviewsByUserId() {
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);
        when(reviewRepository.findByUserId(1)).thenReturn(reviews);

        List<Review> result = reviewService.getReviewsByUserId(1);
        assertEquals(2, result.size());
    }

    @Test
    public void testCreateReview() {
        Review review = new Review();
        review.setRating(5); // Set a valid rating
        User user = new User();
        Book book = new Book();
        when(reviewRepository.existsByBookIdAndUserId(1, 1)).thenReturn(false);
        when(userService.getUserById(1)).thenReturn(user);
        when(bookService.getBookById(1)).thenReturn(book);
        when(reviewRepository.save(review)).thenReturn(review);

        Review result = reviewService.createReview(1, 1, review);
        assertEquals(review, result);
    }

    @Test
    public void testCreateReviewAlreadyExists() {
        Review review = new Review();
        when(reviewRepository.existsByBookIdAndUserId(1, 1)).thenReturn(true);

        assertThrows(BadRequestException.class, () -> reviewService.createReview(1, 1, review));
    }

    @Test
    public void testUpdateReview() {
        Review existingReview = new Review();
        Review updatedReview = new Review();
        updatedReview.setRating(4); // Set a valid rating
        when(reviewRepository.findByBookIdAndUserId(1, 1)).thenReturn(Optional.of(existingReview));
        when(reviewRepository.save(existingReview)).thenReturn(existingReview);

        Review result = reviewService.updateReview(1, 1, updatedReview);
        assertEquals(existingReview, result);
    }

    @Test
    public void testUpdateReviewNotFound() {
        Review updatedReview = new Review();
        when(reviewRepository.findByBookIdAndUserId(1, 1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> reviewService.updateReview(1, 1, updatedReview));
    }

    @Test
    public void testDeleteReview() {
        when(reviewRepository.existsById(1)).thenReturn(true);

        reviewService.deleteReview(1);

        verify(reviewRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteReviewNotFound() {
        when(reviewRepository.existsById(1)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> reviewService.deleteReview(1));
    }
}
