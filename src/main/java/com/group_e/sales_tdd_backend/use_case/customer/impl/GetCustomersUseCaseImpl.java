package com.group_e.sales_tdd_backend.use_case.customer.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Customer;
import com.group_e.sales_tdd_backend.repository.CustomerRepository;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomersUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@UseCase("getCustomersUseCase")
public class GetCustomersUseCaseImpl implements GetCustomersUseCase {
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> execute() {
        return customerRepository.findAll();
    }
}
