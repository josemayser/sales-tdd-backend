package com.group_e.sales_tdd_backend.use_case.customer.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Customer;
import com.group_e.sales_tdd_backend.exception.customer.CustomerNotFoundException;
import com.group_e.sales_tdd_backend.repository.CustomerRepository;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomerByIdUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@UseCase("getCustomerByIdUseCase")
public class GetCustomerByIdUseCaseImpl implements GetCustomerByIdUseCase {
    private final CustomerRepository customerRepository;

    @Override
    public Customer execute(UUID id) throws CustomerNotFoundException {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(
                String.format("Customer with id: %s not found.", id)
        ));
    }
}
