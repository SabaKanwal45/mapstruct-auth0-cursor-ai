package com.mapstruct.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewWithBookDto extends ReviewBaseDto {
    private BookSlimDto book;
} 