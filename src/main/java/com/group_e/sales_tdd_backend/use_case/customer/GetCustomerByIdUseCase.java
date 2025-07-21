package com.group_e.sales_tdd_backend.use_case.customer;

import com.group_e.sales_tdd_backend.entity.Customer;
import com.group_e.sales_tdd_backend.exception.customer.CustomerNotFoundException;

import java.util.UUID;

public interface GetCustomerByIdUseCase {
    Customer execute(UUID id) throws CustomerNotFoundException;
}
