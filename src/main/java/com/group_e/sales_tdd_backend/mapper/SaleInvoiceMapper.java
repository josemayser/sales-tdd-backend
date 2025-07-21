package com.group_e.sales_tdd_backend.mapper;

import com.group_e.sales_tdd_backend.dto.response.SaleInvoiceResponse;
import com.group_e.sales_tdd_backend.entity.SaleInvoice;
import org.mapstruct.Mapper;

@Mapper(uses = {CustomerMapper.class, WarehouseMapper.class, PaymentConditionMapper.class})
public interface SaleInvoiceMapper {
    SaleInvoiceResponse toResponse(SaleInvoice saleInvoice);
}
