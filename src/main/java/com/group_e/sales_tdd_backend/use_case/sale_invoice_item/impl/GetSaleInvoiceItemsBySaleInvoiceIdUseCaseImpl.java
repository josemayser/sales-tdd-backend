package com.group_e.sales_tdd_backend.use_case.sale_invoice_item.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.SaleInvoiceItem;
import com.group_e.sales_tdd_backend.repository.SaleInvoiceItemRepository;
import com.group_e.sales_tdd_backend.use_case.sale_invoice_item.GetSaleInvoiceItemsBySaleInvoiceIdUseCase;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@UseCase("getSaleInvoiceItemsBySaleInvoiceIdUseCase")
public class GetSaleInvoiceItemsBySaleInvoiceIdUseCaseImpl implements GetSaleInvoiceItemsBySaleInvoiceIdUseCase {
    private final SaleInvoiceItemRepository saleInvoiceItemRepository;

    @Override
    public List<SaleInvoiceItem> execute(UUID saleInvoiceId) {
        return saleInvoiceItemRepository.findAllBySaleInvoiceId(saleInvoiceId);
    }
}
