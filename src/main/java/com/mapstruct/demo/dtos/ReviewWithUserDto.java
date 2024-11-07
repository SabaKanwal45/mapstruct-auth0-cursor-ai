package com.mapstruct.demo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewWithUserDto extends ReviewBaseDto {
    private UserGetDto user;
} 