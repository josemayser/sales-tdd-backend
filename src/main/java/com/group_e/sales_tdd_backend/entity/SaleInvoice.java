package com.group_e.sales_tdd_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "sale_invoices")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SaleInvoice {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "warehouse_id", nullable = false)
    private UUID warehouseId;

    @Column(name = "payment_condition_id", nullable = false)
    private UUID paymentConditionId;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "customer_group_discount_percentage", nullable = false, precision = 5, scale = 2)
    private BigDecimal customerGroupDiscountPercentage = BigDecimal.ZERO;

    @Column(name = "tax_name", nullable = false, length = 256)
    private String taxName;

    @Column(name = "tax_id", nullable = false, length = 256)
    private String taxId;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_condition_id", referencedColumnName = "id", insertable = false, updatable = false)
    private PaymentCondition paymentCondition;
}
