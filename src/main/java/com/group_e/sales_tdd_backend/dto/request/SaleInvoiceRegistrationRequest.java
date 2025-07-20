package com.group_e.sales_tdd_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
public class SaleInvoiceRegistrationRequest {
    private UUID customerId;
    private UUID warehouseId;
    private UUID paymentConditionId;
    private String taxName;
    private String taxId;
    private List<SaleInvoiceItemRegistrationRequest> saleInvoiceItems;
}
