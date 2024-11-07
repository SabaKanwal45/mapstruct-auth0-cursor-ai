package com.mapstruct.demo.controllers;

import com.mapstruct.demo.dtos.*;
import com.mapstruct.demo.entities.Review;
import com.mapstruct.demo.mappers.ReviewMapper;
import com.mapstruct.demo.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping
    public ResponseEntity<List<ReviewMetadataDto>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviewMapper.reviewsToMetadataDtos(reviews));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewGetDto> getReviewById(@PathVariable Integer id) {
        log.info("Fetching complete review details for id: {}", id);
        Review review = reviewService.getReviewById(id);
        return ResponseEntity.ok(reviewMapper.reviewToReviewGetDto(review));
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<List<ReviewWithUserDto>> getReviewsByBook(@PathVariable Integer bookId) {
        List<Review> reviews = reviewService.getReviewsByBookId(bookId);
        return ResponseEntity.ok(reviewMapper.reviewsToReviewWithUserDtos(reviews));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ReviewWithBookDto>> getReviewsByUser(@PathVariable Integer userId) {
        List<Review> reviews = reviewService.getReviewsByUserId(userId);
        return ResponseEntity.ok(reviewMapper.reviewsToReviewWithBookDtos(reviews));
    }

    @PostMapping("/users/{userId}/books/{bookId}")
    public ResponseEntity<ReviewMetadataDto> createReview(
            @PathVariable Integer userId,
            @PathVariable Integer bookId,
            @Valid @RequestBody ReviewPostDto reviewPostDto) {
        log.info("Received review creation request for userId: {} and bookId: {}", userId, bookId);
        try {
            Review review = reviewMapper.reviewPostDtoToReview(reviewPostDto);
            log.debug("Mapped ReviewPostDto to Review entity");
            
            Review savedReview = reviewService.createReview(userId, bookId, review);
            log.debug("Review saved successfully with id: {}", savedReview.getId());
            
            ReviewMetadataDto responseDto = reviewMapper.reviewToMetadataDto(savedReview);
            log.info("Successfully created review with id: {}", responseDto.getId());
            
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(responseDto);
        } catch (Exception e) {
            log.error("Error creating review", e);
            throw e;
        }
    }

    @PutMapping("/users/{userId}/books/{bookId}")
    public ResponseEntity<ReviewMetadataDto> updateReview(
            @PathVariable Integer userId,
            @PathVariable Integer bookId,
            @Valid @RequestBody ReviewPostDto reviewPostDto) {
        Review review = reviewMapper.reviewPostDtoToReview(reviewPostDto);
        Review updatedReview = reviewService.updateReview(userId, bookId, review);
        return ResponseEntity.ok(reviewMapper.reviewToMetadataDto(updatedReview));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/books/{bookId}/metadata")
    public ResponseEntity<List<ReviewMetadataDto>> getReviewsByBookMetadata(@PathVariable Integer bookId) {
        log.info("Fetching review metadata for book id: {}", bookId);
        List<Review> reviews = reviewService.getReviewsByBookId(bookId);
        return ResponseEntity.ok(reviewMapper.reviewsToMetadataDtos(reviews));
    }

    @GetMapping("/users/{userId}/metadata")
    public ResponseEntity<List<ReviewMetadataDto>> getReviewsByUserMetadata(@PathVariable Integer userId) {
        log.info("Fetching review metadata for user id: {}", userId);
        List<Review> reviews = reviewService.getReviewsByUserId(userId);
        return ResponseEntity.ok(reviewMapper.reviewsToMetadataDtos(reviews));
    }
} 