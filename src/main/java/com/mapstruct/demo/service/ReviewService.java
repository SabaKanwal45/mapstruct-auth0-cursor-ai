package com.mapstruct.demo.service;

import com.mapstruct.demo.entities.Review;
import com.mapstruct.demo.entities.Book;
import com.mapstruct.demo.entities.User;
import com.mapstruct.demo.exception.BadRequestException;
import com.mapstruct.demo.exception.ResourceNotFoundException;
import com.mapstruct.demo.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookService bookService;
    private final UserService userService;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Integer id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
    }

    public List<Review> getReviewsByBookId(Integer bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public List<Review> getReviewsByUserId(Integer userId) {
        return reviewRepository.findByUserId(userId);
    }

    @Transactional
    public Review createReview(Integer userId, Integer bookId, Review review) {
        log.info("Creating review for userId: {} and bookId: {}", userId, bookId);
        
        if (reviewRepository.existsByBookIdAndUserId(bookId, userId)) {
            log.warn("User {} has already reviewed book {}", userId, bookId);
            throw new BadRequestException("User has already reviewed this book");
        }

        try {
            validateRating(review.getRating());
            
            User user = userService.getUserById(userId);
            log.debug("Found user: {}", user.getId());
            
            Book book = bookService.getBookById(bookId);
            log.debug("Found book: {}", book.getId());

            review.setUser(user);
            review.setBook(book);
            review.setCreatedAt(LocalDateTime.now());

            Review savedReview = reviewRepository.save(review);
            log.info("Successfully created review with id: {}", savedReview.getId());
            
            return savedReview;
        } catch (Exception e) {
            log.error("Error creating review", e);
            throw e;
        }
    }

    @Transactional
    public Review updateReview(Integer userId, Integer bookId, Review updatedReview) {
        Review existingReview = reviewRepository.findByBookIdAndUserId(bookId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found for this user and book"));

        validateRating(updatedReview.getRating());

        existingReview.setRating(updatedReview.getRating());
        existingReview.setReviewText(updatedReview.getReviewText());

        return reviewRepository.save(existingReview);
    }

    public void deleteReview(Integer id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }

    private void validateRating(Integer rating) {
        if (rating == null || rating < 1 || rating > 5) {
            log.warn("Invalid rating value: {}", rating);
            throw new BadRequestException("Rating must be between 1 and 5");
        }
    }
} 