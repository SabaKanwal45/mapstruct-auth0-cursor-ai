package com.mapstruct.demo.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "book_review",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"book_id", "user_id"},
        name = "uk_book_user_review"
    ))
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer rating;

    @Column(name = "review_text", length = 1000)
    private String reviewText;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
} 