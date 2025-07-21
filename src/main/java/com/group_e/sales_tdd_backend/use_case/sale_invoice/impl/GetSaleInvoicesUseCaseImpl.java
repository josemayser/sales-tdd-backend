package com.group_e.sales_tdd_backend.use_case.sale_invoice.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.SaleInvoice;
import com.group_e.sales_tdd_backend.repository.SaleInvoiceRepository;
import com.group_e.sales_tdd_backend.use_case.sale_invoice.GetSaleInvoicesUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@UseCase("getSaleInvoicesUseCase")
public class GetSaleInvoicesUseCaseImpl implements GetSaleInvoicesUseCase {
    private final SaleInvoiceRepository saleInvoiceRepository;

    @Override
    public List<SaleInvoice> execute() {
        return saleInvoiceRepository.findAll();
    }
}
