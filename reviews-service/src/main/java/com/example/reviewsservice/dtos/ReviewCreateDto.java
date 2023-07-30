package com.example.reviewsservice.dtos;

import com.example.reviewsservice.models.Review;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.MessageInterpolator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * DTO for {@link Review}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ReviewCreateDto(
        @NotNull
        Long userId,
        @NotNull
        Long productId,
        @NotNull
        Long orderId,
        @NotBlank
        String reviewText,
        @Range(min = 0, max = 5)
        int rating) implements Serializable {
}