package com.group_e.sales_tdd_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class SaleInvoiceRegistrationRequest {
    @NotNull(message = "The customer id is required")
    private UUID customerId;

    @NotNull(message = "The warehouse id is required")
    private UUID warehouseId;

    @NotNull(message = "The payment condition id is required")
    private UUID paymentConditionId;

    @NotBlank(message = "The tax name is required")
    private String taxName;

    @NotBlank(message = "The tax id is required")
    private String taxId;

    @NotEmpty(message = "Sale invoice items must not be empty")
    private List<SaleInvoiceItemRegistrationRequest> saleInvoiceItems;
}
