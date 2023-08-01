package com.example.orderservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.orderservice.models.OrderProduct}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OrderProductDto(
        Long productId,
        int quantity,
        BigDecimal price
) implements Serializable {
}