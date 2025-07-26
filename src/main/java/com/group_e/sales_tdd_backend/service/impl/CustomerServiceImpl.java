package com.group_e.sales_tdd_backend.service.impl;

import com.group_e.sales_tdd_backend.dto.request.CustomerRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.CustomerResponse;
import com.group_e.sales_tdd_backend.entity.Customer;
import com.group_e.sales_tdd_backend.entity.CustomerGroup;
import com.group_e.sales_tdd_backend.exception.customer.CustomerAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.customer.CustomerNotFoundException;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupNotFoundException;
import com.group_e.sales_tdd_backend.mapper.CustomerMapper;
import com.group_e.sales_tdd_backend.service.CustomerService;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomerByCodeUseCase;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomersUseCase;
import com.group_e.sales_tdd_backend.use_case.customer.SaveCustomerUseCase;
import com.group_e.sales_tdd_backend.use_case.customer_group.GetCustomerGroupByIdUseCase;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final GetCustomersUseCase getCustomersUseCase;
    private final GetCustomerGroupByIdUseCase getCustomerGroupByIdUseCase;
    private final GetCustomerByCodeUseCase getCustomerByCodeUseCase;
    private final SaveCustomerUseCase saveCustomerUseCase;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerResponse> getCustomers() {
        return customerMapper.toResponses(getCustomersUseCase.execute());
    }

    @Override
    public CustomerResponse registerCustomer(CustomerRegistrationRequest request)
            throws CustomerGroupNotFoundException, CustomerAlreadyExistsException, AddressException {
        isValidEmail(request.getEmail());
        CustomerGroup customerGroup = getCustomerGroupByIdUseCase.execute(request.getCustomerGroupId());
        String code = request.getCode();
        try {
            getCustomerByCodeUseCase.execute(code.toUpperCase());
            throw new CustomerAlreadyExistsException(
                    String.format("A Customer with the code: %s already exists.", code)
            );
        } catch (CustomerNotFoundException ignored) {
        }
        Customer newCustomer = new Customer();
        newCustomer.setCustomerGroupId(customerGroup.getId());
        newCustomer.setName(request.getName().toUpperCase());
        newCustomer.setCode(code.toUpperCase());
        Customer savedCustomer = saveCustomerUseCase.execute(newCustomer);
        savedCustomer.setCustomerGroup(customerGroup);
        return customerMapper.toResponse(savedCustomer);
    }

    private boolean isValidEmail(String email) throws AddressException {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException ex) {
            throw ex;
        }
    }
}
