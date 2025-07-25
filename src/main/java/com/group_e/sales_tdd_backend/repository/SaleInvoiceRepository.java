package com.group_e.sales_tdd_backend.repository;

import com.group_e.sales_tdd_backend.entity.SaleInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SaleInvoiceRepository extends JpaRepository<SaleInvoice, UUID> {
}
