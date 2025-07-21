package com.group_e.sales_tdd_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PaymentConditionRegistrationRequest {
    @NotBlank(message = "The name is required")
    private String name;
}
