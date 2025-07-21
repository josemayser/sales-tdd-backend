package com.group_e.sales_tdd_backend.service;

import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceItemRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.SaleInvoiceResponse;
import com.group_e.sales_tdd_backend.entity.*;
import com.group_e.sales_tdd_backend.mapper.SaleInvoiceItemMapper;
import com.group_e.sales_tdd_backend.mapper.SaleInvoiceMapper;
import com.group_e.sales_tdd_backend.service.impl.SaleInvoiceServiceImpl;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomerByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.product.GetProductByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice.SaveSaleInvoiceUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice_item.SaveAllSaleInvoiceItemsUseCase;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehouseByIdUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaleInvoiceServiceTest {
    @InjectMocks
    private SaleInvoiceServiceImpl saleInvoiceService;
    @Mock
    private GetCustomerByIdUseCase getCustomerByIdUseCase;
    @Mock
    private GetWarehouseByIdUseCase getWarehouseByIdUseCase;
    @Mock
    private GetPaymentConditionByIdUseCase getPaymentConditionByIdUseCase;
    @Mock
    private GetProductByIdUseCase getProductByIdUseCase;
    @Mock
    private SaveSaleInvoiceUseCase saveSaleInvoiceUseCase;
    @Mock
    private SaveAllSaleInvoiceItemsUseCase saveAllSaleInvoiceItemsUseCase;
    @Mock
    private SaleInvoiceMapper saleInvoiceMapper;
    @Mock
    private SaleInvoiceItemMapper saleInvoiceItemMapper;

    @Captor
    private ArgumentCaptor<SaleInvoice> saleInvoiceCaptor;
    @Captor
    private ArgumentCaptor<List<SaleInvoiceItem>> saleInvoiceItemsCaptor;


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

        SaleInvoice saleInvoice = new SaleInvoice();
        saleInvoice.setId(UUID.randomUUID());

        when(getCustomerByIdUseCase.execute(customer.getId())).thenReturn(customer);
        when(getWarehouseByIdUseCase.execute(warehouse.getId())).thenReturn(warehouse);
        when(getPaymentConditionByIdUseCase.execute(paymentCondition.getId())).thenReturn(paymentCondition);
        when(getProductByIdUseCase.execute(product.getId())).thenReturn(product);
        when(saveSaleInvoiceUseCase.execute(any())).thenReturn(saleInvoice);
        when(saleInvoiceMapper.toResponse(any())).thenReturn(new SaleInvoiceResponse());

        saleInvoiceService.registerSaleInvoice(request);

        verify(saveSaleInvoiceUseCase).execute(saleInvoiceCaptor.capture());
        SaleInvoice capturedSaleInvoice = saleInvoiceCaptor.getValue();

        assertThat(capturedSaleInvoice.getCustomerGroupDiscountPercentage()).isEqualByComparingTo("8.00");
        assertThat(capturedSaleInvoice.getTotalAmount()).isEqualByComparingTo("89.00");

        verify(saveAllSaleInvoiceItemsUseCase).execute(saleInvoiceItemsCaptor.capture());
        List<SaleInvoiceItem> capturedSaleInvoiceItems = saleInvoiceItemsCaptor.getValue();
        SaleInvoiceItem saleInvoiceItem = capturedSaleInvoiceItems.getFirst();

        assertThat(saleInvoiceItem.getUnitPrice()).isEqualByComparingTo("50.00");
        assertThat(saleInvoiceItem.getQuantity()).isEqualByComparingTo(2);
        assertThat(saleInvoiceItem.getProductGroupDiscountPercentage()).isEqualByComparingTo("3.00");
        assertThat(saleInvoiceItem.getTotalDiscountPercentage()).isEqualByComparingTo("11.00");
        assertThat(saleInvoiceItem.getTotalDiscountAmount()).isEqualByComparingTo("11.00");
        assertThat(saleInvoiceItem.getFinalAmount()).isEqualByComparingTo("89.00");
    }
}
