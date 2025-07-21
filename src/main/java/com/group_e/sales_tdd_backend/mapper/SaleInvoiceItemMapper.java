package com.group_e.sales_tdd_backend.mapper;

import com.group_e.sales_tdd_backend.dto.response.SaleInvoiceItemResponse;
import com.group_e.sales_tdd_backend.entity.SaleInvoiceItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {ProductMapper.class})
public interface SaleInvoiceItemMapper {
    List<SaleInvoiceItemResponse> toResponses(List<SaleInvoiceItem> saleInvoiceItems);
}
