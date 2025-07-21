package com.group_e.sales_tdd_backend.use_case.sale_invoice_item.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.SaleInvoiceItem;
import com.group_e.sales_tdd_backend.repository.SaleInvoiceItemRepository;
import com.group_e.sales_tdd_backend.use_case.sale_invoice_item.SaveAllSaleInvoiceItemsUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@UseCase("saveAllSaleInvoiceItemsUseCase")
public class SaveAllSaleInvoiceItemsUseCaseImpl implements SaveAllSaleInvoiceItemsUseCase {
    private final SaleInvoiceItemRepository saleInvoiceItemRepository;

    @Override
    public List<SaleInvoiceItem> execute(List<SaleInvoiceItem> saleInvoiceItems) {
        return saleInvoiceItemRepository.saveAllAndFlush(saleInvoiceItems);
    }
}
