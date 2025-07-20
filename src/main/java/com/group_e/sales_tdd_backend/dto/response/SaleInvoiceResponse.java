package com.group_e.sales_tdd_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleInvoiceResponse {
    private UUID id;
    private UUID customerId;
    private UUID warehouseId;
    private UUID paymentConditionId;
    private Timestamp createdAt;
    private BigDecimal customerGroupDiscountPercentage;
    private String taxName;
    private String taxId;
    private BigDecimal totalAmount;
    private CustomerResponse customer;
    private WarehouseResponse warehouse;
    private PaymentConditionResponse paymentCondition;
    private List<SaleInvoiceItemResponse> saleInvoiceItems;
}
