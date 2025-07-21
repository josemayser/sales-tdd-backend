package com.group_e.sales_tdd_backend.use_case.customer;

import com.group_e.sales_tdd_backend.entity.Customer;

import java.util.List;

public interface GetCustomersUseCase {
    List<Customer> execute();
}
