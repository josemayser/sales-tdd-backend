package com.group_e.sales_tdd_backend.steps;

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
import com.group_e.sales_tdd_backend.service.impl.SaleInvoiceServiceImpl;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomerByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.product.GetProductByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice.SaveSaleInvoiceUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice_item.SaveAllSaleInvoiceItemsUseCase;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehouseByIdUseCase;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaleInvoiceSteps {

    @InjectMocks
    private SaleInvoiceServiceImpl saleInvoiceService;

    @Mock
    private GetCustomerByIdUseCase getCustomerByIdUseCase;
    @Mock
    private GetProductByIdUseCase getProductByIdUseCase;
    @Mock
    private GetWarehouseByIdUseCase getWarehouseByIdUseCase;
    @Mock
    private GetPaymentConditionByIdUseCase getPaymentConditionByIdUseCase;
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

    private Customer customer;
    private Product product;
    private SaleInvoice response;

    private final Timestamp now = new Timestamp(System.currentTimeMillis());

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("un cliente del grupo {string} con {double}% de descuento")
    public void unClienteDelGrupoConDescuento(String grupoNombre, double descuento) throws CustomerNotFoundException {
        CustomerGroup group = new CustomerGroup(UUID.randomUUID(), now, now, grupoNombre, BigDecimal.valueOf(descuento));
        customer = new Customer(UUID.randomUUID(), group.getId(), now, now, "Juan Pérez", "CUS-001", "12345678", "test@test.com", group);
        when(getCustomerByIdUseCase.execute(customer.getId())).thenReturn(customer);
    }

    @And("un producto del grupo {string} con {double}% de descuento y precio {double}")
    public void unProductoDelGrupoConDescuento(String grupoNombre, double descuento, double precio) throws ProductNotFoundException {
        ProductGroup group = new ProductGroup(UUID.randomUUID(), now, now, grupoNombre, BigDecimal.valueOf(descuento));
        product = new Product(UUID.randomUUID(), group.getId(), now, now, "Taladro", "PR0-001", BigDecimal.valueOf(precio), group);
        when(getProductByIdUseCase.execute(product.getId())).thenReturn(product);
    }

    @When("se registra una factura con {int} unidades del producto")
    public void seRegistraUnaFacturaConUnidadesDelProducto(int cantidad) throws WarehouseNotFoundException, PaymentConditionNotFoundException, CustomerNotFoundException, ProductNotFoundException {
        Warehouse warehouse = new Warehouse(UUID.randomUUID(), now, now, "Almacén", "Zona Norte");
        PaymentCondition condition = new PaymentCondition(UUID.randomUUID(), now, now, "Contado");

        when(getWarehouseByIdUseCase.execute(warehouse.getId())).thenReturn(warehouse);
        when(getPaymentConditionByIdUseCase.execute(condition.getId())).thenReturn(condition);

        SaleInvoiceRegistrationRequest request = new SaleInvoiceRegistrationRequest(
                customer.getId(),
                warehouse.getId(),
                condition.getId(),
                "Juan Pérez",
                "12345678",
                List.of(new SaleInvoiceItemRegistrationRequest(product.getId(), cantidad))
        );

        SaleInvoice saleInvoice = new SaleInvoice();
        saleInvoice.setId(UUID.randomUUID());

        when(saveSaleInvoiceUseCase.execute(any())).thenReturn(saleInvoice);
        when(saleInvoiceMapper.toResponse(any())).thenReturn(new SaleInvoiceResponse());
        saleInvoiceService.registerSaleInvoice(request);
    }

    @Then("el total de la factura debe ser {double}")
    public void elTotalDeLaFacturaDebeSer(Double totalEsperado) {
        verify(saveSaleInvoiceUseCase).execute(saleInvoiceCaptor.capture());
        response = saleInvoiceCaptor.getValue();

        Assertions.assertThat(response.getTotalAmount()).isEqualByComparingTo(BigDecimal.valueOf(totalEsperado));
    }

    @And("el total del descuento aplicado debe ser {double}")
    public void elTotalDelDescuentoAplicadoDebeSer(Double totalDescuento) {
        double montoTotal = 100.00;
        double montoEsperado = montoTotal - totalDescuento;
        Assertions.assertThat(response.getTotalAmount()).isEqualByComparingTo(BigDecimal.valueOf(montoEsperado));
    }
}