package com.group_e.sales_tdd_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class SaleInvoiceItemRegistrationRequest {
    private UUID productId;
    private Integer quantity;
}
