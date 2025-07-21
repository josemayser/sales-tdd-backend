package com.group_e.sales_tdd_backend.use_case.sale_invoice_item;

import com.group_e.sales_tdd_backend.entity.SaleInvoiceItem;

import java.util.List;
import java.util.UUID;

public interface GetSaleInvoiceItemsBySaleInvoiceIdUseCase {
    List<SaleInvoiceItem> execute(UUID saleInvoiceId);
}
