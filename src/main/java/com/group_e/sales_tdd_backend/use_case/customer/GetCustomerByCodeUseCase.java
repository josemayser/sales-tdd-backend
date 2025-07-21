package com.group_e.sales_tdd_backend.use_case.customer;

import com.group_e.sales_tdd_backend.entity.Customer;
import com.group_e.sales_tdd_backend.exception.customer.CustomerNotFoundException;

public interface GetCustomerByCodeUseCase {
    Customer execute(String code) throws CustomerNotFoundException;
}
