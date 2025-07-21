package com.group_e.sales_tdd_backend.use_case.sale_invoice_item;

import com.group_e.sales_tdd_backend.entity.SaleInvoiceItem;

import java.util.List;

public interface SaveAllSaleInvoiceItemsUseCase {
    List<SaleInvoiceItem> execute(List<SaleInvoiceItem> saleInvoiceItems);
}
