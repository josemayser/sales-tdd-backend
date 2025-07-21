package com.group_e.sales_tdd_backend.use_case.customer;

import com.group_e.sales_tdd_backend.entity.Customer;

public interface SaveCustomerUseCase {
    Customer execute(Customer customer);
}
