package com.group_e.sales_tdd_backend.service;

import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.SaleInvoiceResponse;

public interface SaleInvoiceService {
    SaleInvoiceResponse registerSaleInvoice(SaleInvoiceRegistrationRequest request);
}
