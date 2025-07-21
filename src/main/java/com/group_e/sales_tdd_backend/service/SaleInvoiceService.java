package com.group_e.sales_tdd_backend.service;

import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.SaleInvoiceResponse;
import com.group_e.sales_tdd_backend.exception.customer.CustomerNotFoundException;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionNotFoundException;
import com.group_e.sales_tdd_backend.exception.product.ProductNotFoundException;
import com.group_e.sales_tdd_backend.exception.warehouse.WarehouseNotFoundException;

import java.util.List;

public interface SaleInvoiceService {
    List<SaleInvoiceResponse> getSaleInvoices();

    SaleInvoiceResponse registerSaleInvoice(SaleInvoiceRegistrationRequest request) throws CustomerNotFoundException, WarehouseNotFoundException, PaymentConditionNotFoundException, ProductNotFoundException;
}
