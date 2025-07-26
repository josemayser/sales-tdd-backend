package com.group_e.sales_tdd_backend.steps;

import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceItemRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.request.SaleInvoiceRegistrationRequest;
import com.group_e.sales_tdd_backend.entity.*;
import com.group_e.sales_tdd_backend.exception.customer.CustomerNotFoundException;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionNotFoundException;
import com.group_e.sales_tdd_backend.exception.product.ProductNotFoundException;
import com.group_e.sales_tdd_backend.exception.warehouse.WarehouseNotFoundException;
import com.group_e.sales_tdd_backend.mapper.SaleInvoiceItemMapper;
import com.group_e.sales_tdd_backend.mapper.SaleInvoiceMapper;
import com.group_e.sales_tdd_backend.service.SaleInvoiceService;
import com.group_e.sales_tdd_backend.service.impl.SaleInvoiceServiceImpl;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomerByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.product.GetProductByIdUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice.SaveSaleInvoiceUseCase;
import com.group_e.sales_tdd_backend.use_case.sale_invoice_item.SaveAllSaleInvoiceItemsUseCase;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehouseByIdUseCase;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.mockito.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

public class RegisterSaleInvoiceSteps {

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
    private SaleInvoiceRegistrationRequest request;
    private Exception caughtException;

    private final Timestamp now = new Timestamp(System.currentTimeMillis());

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Given("que la solicitud de factura tiene todos los campos válidos excepto el taxName que es nulo")
    public void que_la_solicitud_de_factura_tiene_todos_los_campos_validos_exepto_el_taxname_que_es_nulo() throws CustomerNotFoundException, ProductNotFoundException, WarehouseNotFoundException, PaymentConditionNotFoundException {
        CustomerGroup group = new CustomerGroup(UUID.randomUUID(), now, now, "GrupoTest", BigDecimal.valueOf(10));
        customer = new Customer(UUID.randomUUID(), group.getId(), now, now, "Juan Pérez", "CUS-001", "12345678", "test@test.com", group);
        when(getCustomerByIdUseCase.execute(customer.getId())).thenReturn(customer);
        ProductGroup productGroup = new ProductGroup(UUID.randomUUID(), now, now, "GrupoTest", BigDecimal.valueOf(0));
        product = new Product(UUID.randomUUID(), group.getId(), now, now, "Taladro", "PR0-001", BigDecimal.valueOf(50), productGroup);
        when(getProductByIdUseCase.execute(product.getId())).thenReturn(product);
        Warehouse warehouse = new Warehouse(UUID.randomUUID(), now, now, "Almacén", "Zona Norte");
        PaymentCondition condition = new PaymentCondition(UUID.randomUUID(), now, now, "Contado");

        when(getWarehouseByIdUseCase.execute(warehouse.getId())).thenReturn(warehouse);
        when(getPaymentConditionByIdUseCase.execute(condition.getId())).thenReturn(condition);

        request = new SaleInvoiceRegistrationRequest(
                customer.getId(),
                warehouse.getId(),
                condition.getId(),
                null,
                "12345678",
                List.of(new SaleInvoiceItemRegistrationRequest(product.getId(), 1))
        );

    }

    @When("intento registrar la factura de venta")
    public void intento_registrar_la_factura_de_venta() {
        try {
            saleInvoiceService.registerSaleInvoice(request);
        } catch (Exception e) {
            caughtException = e;
        }
    }

    @Then("se lanza una excepción con el mensaje {string}")
    public void se_lanza_una_excepcion_con_el_mensaje(String expectedMessage) {
        Assertions.assertEquals(expectedMessage, caughtException.getMessage());
    }
}
