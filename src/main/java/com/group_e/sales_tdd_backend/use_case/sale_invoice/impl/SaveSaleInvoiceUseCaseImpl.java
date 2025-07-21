package com.group_e.sales_tdd_backend.use_case.sale_invoice.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.SaleInvoice;
import com.group_e.sales_tdd_backend.repository.SaleInvoiceRepository;
import com.group_e.sales_tdd_backend.use_case.sale_invoice.SaveSaleInvoiceUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("saveSaleInvoiceUseCase")
public class SaveSaleInvoiceUseCaseImpl implements SaveSaleInvoiceUseCase {
    private final SaleInvoiceRepository saleInvoiceRepository;

    @Override
    public SaleInvoice execute(SaleInvoice saleInvoice) {
        return saleInvoiceRepository.saveAndFlush(saleInvoice);
    }
}
