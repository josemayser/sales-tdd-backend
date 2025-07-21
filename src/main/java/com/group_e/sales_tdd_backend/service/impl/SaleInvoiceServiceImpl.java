package com.group_e.sales_tdd_backend.service.impl;

import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceItemRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.SaleInvoiceResponse;
import com.group_e.sales_tdd_backend.entity.*;
import com.group_e.sales_tdd_backend.mapper.SaleInvoiceItemMapper;
import com.group_e.sales_tdd_backend.mapper.SaleInvoiceMapper;
import com.group_e.sales_tdd_backend.service.SaleInvoiceService;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomerByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.product.GetProductByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice.SaveSaleInvoiceUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice_item.SaveAllSaleInvoiceItemsUseCase;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehouseByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class SaleInvoiceServiceImpl implements SaleInvoiceService {
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final GetWarehouseByIdUseCase getWarehouseByIdUseCase;
    private final GetPaymentConditionByIdUseCase getPaymentConditionByIdUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final SaveSaleInvoiceUseCase saveSaleInvoiceUseCase;
    private final SaveAllSaleInvoiceItemsUseCase saveAllSaleInvoiceItemsUseCase;
    private final SaleInvoiceMapper saleInvoiceMapper;
    private final SaleInvoiceItemMapper saleInvoiceItemMapper;

    @Override
    public SaleInvoiceResponse registerSaleInvoice(SaleInvoiceRegistrationRequest request) {
        Customer customer = getCustomerByIdUseCase.execute(request.getCustomerId());
        Warehouse warehouse = getWarehouseByIdUseCase.execute(request.getWarehouseId());
        PaymentCondition paymentCondition = getPaymentConditionByIdUseCase.execute(request.getPaymentConditionId());
        BigDecimal customerGroupDiscountPercentage = customer.getCustomerGroup().getDiscountPercentage();
        List<SaleInvoiceItem> saleInvoiceItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (SaleInvoiceItemRegistrationRequest item : request.getSaleInvoiceItems()) {
            Product product = getProductByIdUseCase.execute(item.getProductId());
            BigDecimal productPrice = product.getPrice();
            Integer quantity = item.getQuantity();
            BigDecimal productGroupDiscountPercentage = product.getProductGroup().getDiscountPercentage();
            BigDecimal totalDiscountPercentage = customerGroupDiscountPercentage.add(productGroupDiscountPercentage);
            BigDecimal subTotal = productPrice.multiply(BigDecimal.valueOf(quantity));
            BigDecimal totalDiscountAmount = subTotal.multiply(totalDiscountPercentage).divide(BigDecimal.valueOf(100));
            BigDecimal finalAmount = subTotal.subtract(totalDiscountAmount);
            SaleInvoiceItem saleInvoiceItem = new SaleInvoiceItem();
            saleInvoiceItem.setProductId(product.getId());
            saleInvoiceItem.setUnitPrice(productPrice);
            saleInvoiceItem.setQuantity(quantity);
            saleInvoiceItem.setProductGroupDiscountPercentage(productGroupDiscountPercentage);
            saleInvoiceItem.setTotalDiscountPercentage(totalDiscountPercentage);
            saleInvoiceItem.setTotalDiscountAmount(totalDiscountAmount);
            saleInvoiceItem.setFinalAmount(finalAmount);
            saleInvoiceItem.setProduct(product);
            saleInvoiceItems.add(saleInvoiceItem);
            totalAmount = totalAmount.add(finalAmount);
        }
        SaleInvoice newSaleInvoice = new SaleInvoice();
        newSaleInvoice.setCustomerId(customer.getId());
        newSaleInvoice.setWarehouseId(warehouse.getId());
        newSaleInvoice.setPaymentConditionId(paymentCondition.getId());
        newSaleInvoice.setCustomerGroupDiscountPercentage(customerGroupDiscountPercentage);
        newSaleInvoice.setTaxName(request.getTaxName());
        newSaleInvoice.setTaxId(request.getTaxId());
        newSaleInvoice.setTotalAmount(totalAmount);
        SaleInvoice savedSaleInvoice = saveSaleInvoiceUseCase.execute(newSaleInvoice);
        savedSaleInvoice.setCustomer(customer);
        savedSaleInvoice.setWarehouse(warehouse);
        savedSaleInvoice.setPaymentCondition(paymentCondition);
        saleInvoiceItems.forEach(item -> {
            item.setSaleInvoiceId(savedSaleInvoice.getId());
            item.setSaleInvoice(savedSaleInvoice);
        });
        List<SaleInvoiceItem> savedSaleInvoiceItems = saveAllSaleInvoiceItemsUseCase.execute(saleInvoiceItems);
        SaleInvoiceResponse response = saleInvoiceMapper.toResponse(savedSaleInvoice);
        response.setSaleInvoiceItems(saleInvoiceItemMapper.toResponses(savedSaleInvoiceItems));
        return response;
    }
}
