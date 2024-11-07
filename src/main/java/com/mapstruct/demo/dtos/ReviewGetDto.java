package com.mapstruct.demo.dtos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewGetDto {
    private Integer id;
    private Integer rating;
    private String reviewText;
    private LocalDateTime createdAt;
    private BookSlimDto book;
    private UserGetDto user;
} 