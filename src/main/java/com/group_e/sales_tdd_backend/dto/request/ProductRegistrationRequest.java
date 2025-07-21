package com.group_e.sales_tdd_backend.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class ProductRegistrationRequest {
    @NotNull(message = "The product group id is required")
    private UUID productGroupId;

    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The code is required")
    private String code;

    @NotNull(message = "The price is required")
    @DecimalMin(value = "0.0", message = "The price must not be less than 0")
    private BigDecimal price;
}
