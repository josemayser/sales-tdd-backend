package com.group_e.sales_tdd_backend.service.impl;

import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceItemRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.SaleInvoiceResponse;
import com.group_e.sales_tdd_backend.entity.*;
import com.group_e.sales_tdd_backend.exception.customer.CustomerNotFoundException;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionNotFoundException;
import com.group_e.sales_tdd_backend.exception.product.ProductNotFoundException;
import com.group_e.sales_tdd_backend.exception.warehouse.WarehouseNotFoundException;
import com.group_e.sales_tdd_backend.mapper.SaleInvoiceItemMapper;
import com.group_e.sales_tdd_backend.mapper.SaleInvoiceMapper;
import com.group_e.sales_tdd_backend.service.SaleInvoiceService;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomerByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.product.GetProductByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice.GetSaleInvoicesUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice.SaveSaleInvoiceUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice_item.GetSaleInvoiceItemsBySaleInvoiceIdUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice_item.SaveAllSaleInvoiceItemsUseCase;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehouseByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class SaleInvoiceServiceImpl implements SaleInvoiceService {
    private final GetSaleInvoicesUseCase getSaleInvoicesUseCase;
    private final GetSaleInvoiceItemsBySaleInvoiceIdUseCase getSaleInvoiceItemsBySaleInvoiceIdUseCase;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;
    private final GetWarehouseByIdUseCase getWarehouseByIdUseCase;
    private final GetPaymentConditionByIdUseCase getPaymentConditionByIdUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final SaveSaleInvoiceUseCase saveSaleInvoiceUseCase;
    private final SaveAllSaleInvoiceItemsUseCase saveAllSaleInvoiceItemsUseCase;
    private final SaleInvoiceMapper saleInvoiceMapper;
    private final SaleInvoiceItemMapper saleInvoiceItemMapper;

    @Override
    public List<SaleInvoiceResponse> getSaleInvoices() {
        List<SaleInvoice> saleInvoices = getSaleInvoicesUseCase.execute();
        List<SaleInvoiceResponse> responses = new ArrayList<>();
        saleInvoices.forEach(saleInvoice -> {
            List<SaleInvoiceItem> saleInvoiceItems =
                    getSaleInvoiceItemsBySaleInvoiceIdUseCase.execute(saleInvoice.getId());
            SaleInvoiceResponse saleInvoiceResponse = saleInvoiceMapper.toResponse(saleInvoice);
            saleInvoiceResponse.setSaleInvoiceItems(saleInvoiceItemMapper.toResponses(saleInvoiceItems));
            responses.add(saleInvoiceResponse);
        });
        return responses;
    }

    @Override
    public SaleInvoiceResponse registerSaleInvoice(SaleInvoiceRegistrationRequest request)
            throws CustomerNotFoundException, WarehouseNotFoundException, PaymentConditionNotFoundException,
            ProductNotFoundException {
        if (request.getTaxName() == null) {
            throw new IllegalArgumentException("Tax name cannot be null");
        }
        Customer customer = getCustomerByIdUseCase.execute(request.getCustomerId());
        Warehouse warehouse = getWarehouseByIdUseCase.execute(request.getWarehouseId());
        PaymentCondition paymentCondition = getPaymentConditionByIdUseCase.execute(request.getPaymentConditionId());
        BigDecimal customerGroupDiscountPercentage = customer.getCustomerGroup().getDiscountPercentage();
        List<SaleInvoiceItem> newSaleInvoiceItems = new ArrayList<>();
        for (SaleInvoiceItemRegistrationRequest item : request.getSaleInvoiceItems()) {
            newSaleInvoiceItems.add(getNewSaleInvoiceItem(
                    item.getProductId(),
                    item.getQuantity(),
                    customerGroupDiscountPercentage
            ));
        }
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (SaleInvoiceItem newSaleInvoiceItem : newSaleInvoiceItems) {
            totalAmount = totalAmount.add(newSaleInvoiceItem.getFinalAmount());
        }
        SaleInvoice newSaleInvoice = new SaleInvoice();
        newSaleInvoice.setCustomerId(customer.getId());
        newSaleInvoice.setWarehouseId(warehouse.getId());
        newSaleInvoice.setPaymentConditionId(paymentCondition.getId());
        newSaleInvoice.setCustomerGroupDiscountPercentage(customerGroupDiscountPercentage);
        newSaleInvoice.setTaxName(request.getTaxName().toUpperCase());
        newSaleInvoice.setTaxId(request.getTaxId());
        newSaleInvoice.setTotalAmount(totalAmount);
        SaleInvoice savedSaleInvoice = saveSaleInvoiceUseCase.execute(newSaleInvoice);
        savedSaleInvoice.setCustomer(customer);
        savedSaleInvoice.setWarehouse(warehouse);
        savedSaleInvoice.setPaymentCondition(paymentCondition);
        newSaleInvoiceItems.forEach(
                newSaleInvoiceItem -> newSaleInvoiceItem.setSaleInvoiceId(savedSaleInvoice.getId())
        );
        List<SaleInvoiceItem> savedSaleInvoiceItems = saveAllSaleInvoiceItemsUseCase.execute(newSaleInvoiceItems);
        SaleInvoiceResponse response = saleInvoiceMapper.toResponse(savedSaleInvoice);
        response.setSaleInvoiceItems(saleInvoiceItemMapper.toResponses(savedSaleInvoiceItems));
        return response;
    }

    private SaleInvoiceItem getNewSaleInvoiceItem(
            UUID productId,
            Integer quantity,
            BigDecimal customerGroupDiscountPercentage
    ) throws ProductNotFoundException {
        Product product = getProductByIdUseCase.execute(productId);
        BigDecimal productPrice = product.getPrice();
        BigDecimal productGroupDiscountPercentage = product.getProductGroup().getDiscountPercentage();
        BigDecimal totalDiscountPercentage = customerGroupDiscountPercentage.add(productGroupDiscountPercentage);
        BigDecimal subTotal = productPrice.multiply(BigDecimal.valueOf(quantity));
        BigDecimal totalDiscountAmount = subTotal.multiply(totalDiscountPercentage).divide(BigDecimal.valueOf(100));
        SaleInvoiceItem saleInvoiceItem = new SaleInvoiceItem();
        saleInvoiceItem.setProductId(product.getId());
        saleInvoiceItem.setUnitPrice(productPrice);
        saleInvoiceItem.setQuantity(quantity);
        saleInvoiceItem.setProductGroupDiscountPercentage(productGroupDiscountPercentage);
        saleInvoiceItem.setTotalDiscountPercentage(totalDiscountPercentage);
        saleInvoiceItem.setTotalDiscountAmount(totalDiscountAmount);
        saleInvoiceItem.setFinalAmount(subTotal.subtract(totalDiscountAmount));
        saleInvoiceItem.setProduct(product);
        return saleInvoiceItem;
    }
}
