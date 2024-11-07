package com.mapstruct.demo.dtos;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewBaseDto {
    private Integer id;
    private Integer rating;
    private String reviewText;
    private LocalDateTime createdAt;
} 