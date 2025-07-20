package com.group_e.sales_tdd_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleInvoiceItemResponse {
    private UUID id;
    private UUID saleInvoiceId;
    private UUID productId;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal productGroupDiscountPercentage;
    private BigDecimal totalDiscountPercentage;
    private BigDecimal totalDiscountAmount;
    private BigDecimal finalAmount;
    private ProductResponse product;
}
