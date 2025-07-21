package com.group_e.sales_tdd_backend.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SaleInvoiceItemRegistrationRequest {
    @NotNull(message = "The product id is required")
    private UUID productId;

    @NotNull(message = "The quantity is required")
    @Min(value = 1, message = "The quantity must not be less than 1")
    private Integer quantity;
}
