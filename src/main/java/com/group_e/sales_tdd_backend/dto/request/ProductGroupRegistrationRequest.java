package com.group_e.sales_tdd_backend.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductGroupRegistrationRequest {
    @NotBlank(message = "The name is required")
    private String name;

    @DecimalMin(value = "0.0", message = "The discount percentage must not be less than 0")
    @DecimalMax(value = "100.0", message = "The discount percentage must not be greater than 100")
    private BigDecimal discountPercentage;
}
