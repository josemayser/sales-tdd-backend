package com.group_e.sales_tdd_backend.repository;

import com.group_e.sales_tdd_backend.entity.SaleInvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SaleInvoiceItemRepository extends JpaRepository<SaleInvoiceItem, UUID> {
    List<SaleInvoiceItem> findAllBySaleInvoiceId(UUID saleInvoiceId);
}
