package com.group_e.sales_tdd_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "sale_invoice_items")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleInvoiceItem {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "sale_invoice_id", nullable = false)
    private UUID saleInvoiceId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "product_group_discount_percentage", nullable = false, precision = 5, scale = 2)
    private BigDecimal productGroupDiscountPercentage = BigDecimal.ZERO;

    @Column(name = "total_discount_percentage", nullable = false, precision = 5, scale = 2)
    private BigDecimal totalDiscountPercentage = BigDecimal.ZERO;

    @Column(name = "total_discount_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalDiscountAmount;

    @Column(name = "final_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal finalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_invoice_id", referencedColumnName = "id", insertable = false, updatable = false)
    private SaleInvoice saleInvoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Product product;
}
