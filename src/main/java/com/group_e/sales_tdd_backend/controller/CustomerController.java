package com.group_e.sales_tdd_backend.controller;

import com.group_e.sales_tdd_backend.dto.request.CustomerGroupRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.request.CustomerRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.CustomerGroupResponse;
import com.group_e.sales_tdd_backend.dto.response.CustomerResponse;
import com.group_e.sales_tdd_backend.exception.customer.CustomerAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupNotFoundException;
import com.group_e.sales_tdd_backend.service.CustomerGroupService;
import com.group_e.sales_tdd_backend.service.CustomerService;
import jakarta.mail.internet.AddressException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/customers")
public class CustomerController {
    private final CustomerGroupService customerGroupService;
    private final CustomerService customerService;

    @GetMapping("/groups")
    public List<CustomerGroupResponse> getCustomerGroups() {
        return customerGroupService.getCustomerGroups();
    }

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerGroupResponse registerCustomerGroup(@Valid @RequestBody CustomerGroupRegistrationRequest request)
            throws CustomerGroupAlreadyExistsException {
        return customerGroupService.registerCustomerGroup(request);
    }

    @GetMapping
    public List<CustomerResponse> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse registerCustomer(@Valid @RequestBody CustomerRegistrationRequest request)
            throws CustomerGroupNotFoundException, CustomerAlreadyExistsException, AddressException {
        return customerService.registerCustomer(request);
    }
}
