package com.mapstruct.demo.repositories;

import com.mapstruct.demo.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByBookId(Integer bookId);
    List<Review> findByUserId(Integer userId);
    Optional<Review> findByBookIdAndUserId(Integer bookId, Integer userId);
    boolean existsByBookIdAndUserId(Integer bookId, Integer userId);
} 