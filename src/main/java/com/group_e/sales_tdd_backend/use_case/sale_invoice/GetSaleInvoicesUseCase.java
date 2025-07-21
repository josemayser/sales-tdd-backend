package com.group_e.sales_tdd_backend.use_case.sale_invoice;

import com.group_e.sales_tdd_backend.entity.SaleInvoice;

import java.util.List;

public interface GetSaleInvoicesUseCase {
    List<SaleInvoice> execute();
}
