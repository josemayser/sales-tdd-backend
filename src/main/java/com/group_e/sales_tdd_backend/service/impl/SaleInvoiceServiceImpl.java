package com.group_e.sales_tdd_backend.service.impl;

import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.SaleInvoiceResponse;
import com.group_e.sales_tdd_backend.service.SaleInvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SaleInvoiceServiceImpl implements SaleInvoiceService {
    @Override
    public SaleInvoiceResponse registerSaleInvoice(SaleInvoiceRegistrationRequest request) {
        return new SaleInvoiceResponse();
    }
}
