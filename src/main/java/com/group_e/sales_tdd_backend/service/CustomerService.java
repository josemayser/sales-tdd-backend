package com.group_e.sales_tdd_backend.service;

import com.group_e.sales_tdd_backend.dto.request.CustomerRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.CustomerResponse;
import com.group_e.sales_tdd_backend.exception.customer.CustomerAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupNotFoundException;
import jakarta.mail.internet.AddressException;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getCustomers();

    CustomerResponse registerCustomer(CustomerRegistrationRequest request) throws CustomerGroupNotFoundException, CustomerAlreadyExistsException, AddressException;
}
