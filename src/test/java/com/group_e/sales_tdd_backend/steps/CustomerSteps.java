package com.group_e.sales_tdd_backend.steps;

import com.group_e.sales_tdd_backend.dto.request.CustomerRegistrationRequest;
import com.group_e.sales_tdd_backend.entity.CustomerGroup;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupNotFoundException;
import com.group_e.sales_tdd_backend.mapper.CustomerMapper;
import com.group_e.sales_tdd_backend.service.impl.CustomerServiceImpl;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomerByCodeUseCase;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomersUseCase;
import com.group_e.sales_tdd_backend.use_case.customer.SaveCustomerUseCase;
import com.group_e.sales_tdd_backend.use_case.customer_group.GetCustomerGroupByIdUseCase;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.mail.internet.AddressException;
import jakarta.validation.ConstraintViolationException;
import io.cucumber.java.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomerSteps {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private GetCustomerGroupByIdUseCase getCustomerGroupByIdUseCase;

    @Mock
    private GetCustomerByCodeUseCase getCustomerByCodeUseCase;

    @Mock
    private SaveCustomerUseCase saveCustomerUseCase;

    @Mock
    private GetCustomersUseCase getCustomersUseCase;

    @Mock
    private CustomerMapper customerMapper;

    private CustomerRegistrationRequest request;
    private Exception capturedException;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("que el grupo de cliente con ID {string} existe")
    public void que_el_grupo_de_cliente_con_ID_existe(String groupId) throws CustomerGroupNotFoundException {
        UUID groupUUID = UUID.fromString(groupId);
        CustomerGroup customerGroup = new CustomerGroup();
        customerGroup.setId(groupUUID);
        customerGroup.setName("Grupo Test");
        customerGroup.setDiscountPercentage(BigDecimal.valueOf(10));

        when(getCustomerGroupByIdUseCase.execute(groupUUID)).thenReturn(customerGroup);
    }

    @And("un cliente con nombre {string}, código {string}, dni {string}, y email {string} desea registrarse")
    public void un_cliente_desea_registrarse(String nombre, String codigo, String dni, String email) {
        request = new CustomerRegistrationRequest(
                UUID.fromString("b5db4881-54b1-4c64-a2aa-0c79f59d22b0"),
                nombre,
                codigo,
                dni,
                email
        );
    }

    @When("intento registrar al cliente")
    public void intento_registrar_al_cliente() {
        try {
            customerService.registerCustomer(request);
        } catch (Exception e) {
            capturedException = e;
        }
    }

    @Then("debería recibir un error indicando que el email no es válido")
    public void deberia_recibir_un_error_indicando_email_invalido() {
        assertNotNull("Se esperaba una excepción", capturedException);
        assertTrue(capturedException instanceof AddressException);
    }
}
