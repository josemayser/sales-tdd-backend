package com.group_e.sales_tdd_backend.service;

import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceItemRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.SaleInvoiceItemResponse;
import com.group_e.sales_tdd_backend.dto.response.SaleInvoiceResponse;
import com.group_e.sales_tdd_backend.entity.*;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomerByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.product.GetProductByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehouseByIdUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class SaleInvoiceServiceTest {
    @Mock
    private GetCustomerByIdUseCase getCustomerByIdUseCase;
    @Mock
    private GetWarehouseByIdUseCase getWarehouseByIdUseCase;
    @Mock
    private GetPaymentConditionByIdUseCase getPaymentConditionByIdUseCase;
    @Mock
    private GetProductByIdUseCase getProductByIdUseCase;
    @InjectMocks
    private SaleInvoiceService saleInvoiceService;

    @Test
    void shouldCalculateDiscountCorrectly_whenCustomerAndProductHaveDiscounts() {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        UUID customerGroupId = UUID.randomUUID();
        CustomerGroup customerGroup =
                new CustomerGroup(customerGroupId, now, now, "Especialista", new BigDecimal("8.00"));
        Customer customer = new Customer(
                UUID.randomUUID(),
                customerGroupId,
                now,
                now,
                "Juan Pérez",
                "CUS-001",
                customerGroup
        );

        Warehouse warehouse = new Warehouse(UUID.randomUUID(), now, now, "Principal", "Zona Norte");

        PaymentCondition paymentCondition = new PaymentCondition(UUID.randomUUID(), now, now, "Contado");

        UUID productGroupId = UUID.randomUUID();
        ProductGroup productGroup =
                new ProductGroup(productGroupId, now, now, "Herramientas", new BigDecimal("3.00"));
        Product product = new Product(
                UUID.randomUUID(),
                productGroupId,
                now,
                now,
                "Taladro BOSCH",
                "PR0-001",
                new BigDecimal("50.00"),
                productGroup
        );


        SaleInvoiceRegistrationRequest request = new SaleInvoiceRegistrationRequest(
                customer.getId(),
                warehouse.getId(),
                paymentCondition.getId(),
                "Juan Pérez",
                "12345678",
                List.of(new SaleInvoiceItemRegistrationRequest(product.getId(), 2))
        );

        when(getCustomerByIdUseCase.execute(customer.getId())).thenReturn(customer);
        when(getWarehouseByIdUseCase.execute(warehouse.getId())).thenReturn(warehouse);
        when(getPaymentConditionByIdUseCase.execute(paymentCondition.getId())).thenReturn(paymentCondition);
        when(getProductByIdUseCase.execute(product.getId())).thenReturn(product);

        SaleInvoiceResponse response = saleInvoiceService.registerSaleInvoice(request);

        assertThat(response.getCustomerGroupDiscountPercentage()).isEqualByComparingTo("8.00");
        assertThat(response.getTotalAmount()).isEqualByComparingTo("100.00");

        SaleInvoiceItemResponse item = response.getSaleInvoiceItems().getFirst();

        assertThat(item.getUnitPrice()).isEqualByComparingTo("50.00");
        assertThat(item.getQuantity()).isEqualByComparingTo(2);
        assertThat(item.getProductGroupDiscountPercentage()).isEqualByComparingTo("3.00");
        assertThat(item.getTotalDiscountPercentage()).isEqualByComparingTo("11.00");
        assertThat(item.getTotalDiscountAmount()).isEqualByComparingTo("11.00");
        assertThat(item.getFinalAmount()).isEqualByComparingTo("89.00");
    }
}
