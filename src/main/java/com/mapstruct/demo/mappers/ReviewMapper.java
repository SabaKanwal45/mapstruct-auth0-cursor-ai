package com.mapstruct.demo.mappers;

import com.mapstruct.demo.dtos.*;
import com.mapstruct.demo.entities.Review;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BookMapper.class, UserMapper.class})
public interface ReviewMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Review reviewPostDtoToReview(ReviewPostDto reviewPostDto);
    
    @Named("toMetadata")
    ReviewMetadataDto reviewToMetadataDto(Review review);
    
    @Named("toReviewWithBook")
    ReviewWithBookDto reviewToReviewWithBookDto(Review review);
    
    @Named("toReviewWithUser")
    ReviewWithUserDto reviewToReviewWithUserDto(Review review);
    
    @Mapping(target = "book", source = "book")
    @Mapping(target = "user", source = "user")
    ReviewGetDto reviewToReviewGetDto(Review review);
    
    @IterableMapping(qualifiedByName = "toMetadata")
    List<ReviewMetadataDto> reviewsToMetadataDtos(List<Review> reviews);
    
    @IterableMapping(qualifiedByName = "toReviewWithBook")
    List<ReviewWithBookDto> reviewsToReviewWithBookDtos(List<Review> reviews);
    
    @IterableMapping(qualifiedByName = "toReviewWithUser")
    List<ReviewWithUserDto> reviewsToReviewWithUserDtos(List<Review> reviews);
} 