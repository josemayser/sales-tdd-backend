package com.group_e.sales_tdd_backend.use_case.customer.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Customer;
import com.group_e.sales_tdd_backend.repository.CustomerRepository;
import com.group_e.sales_tdd_backend.use_case.customer.SaveCustomerUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("saveCustomerUseCase")
public class SaveCustomerUseCaseImpl implements SaveCustomerUseCase {
    private final CustomerRepository customerRepository;

    @Override
    public Customer execute(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }
}
