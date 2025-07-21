package com.group_e.sales_tdd_backend.use_case.sale_invoice;

import com.group_e.sales_tdd_backend.entity.SaleInvoice;

public interface SaveSaleInvoiceUseCase {
    SaleInvoice execute(SaleInvoice saleInvoice);
}
