package com.group_e.sales_tdd_backend.controller;

import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.SaleInvoiceResponse;
import com.group_e.sales_tdd_backend.exception.customer.CustomerNotFoundException;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionNotFoundException;
import com.group_e.sales_tdd_backend.exception.product.ProductNotFoundException;
import com.group_e.sales_tdd_backend.exception.warehouse.WarehouseNotFoundException;
import com.group_e.sales_tdd_backend.service.SaleInvoiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/sale-invoices")
public class SaleInvoiceController {
    private final SaleInvoiceService saleInvoiceService;

    @GetMapping
    public List<SaleInvoiceResponse> getSaleInvoices() {
        return saleInvoiceService.getSaleInvoices();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleInvoiceResponse registerSaleInvoice(
            @Valid @RequestBody SaleInvoiceRegistrationRequest request
    ) throws CustomerNotFoundException, WarehouseNotFoundException, PaymentConditionNotFoundException,
            ProductNotFoundException {
        return saleInvoiceService.registerSaleInvoice(request);
    }
}
