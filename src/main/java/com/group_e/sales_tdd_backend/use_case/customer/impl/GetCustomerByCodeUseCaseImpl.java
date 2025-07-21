package com.group_e.sales_tdd_backend.use_case.customer.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Customer;
import com.group_e.sales_tdd_backend.exception.customer.CustomerNotFoundException;
import com.group_e.sales_tdd_backend.repository.CustomerRepository;
import com.group_e.sales_tdd_backend.use_case.customer.GetCustomerByCodeUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("getCustomerByCodeUseCase")
public class GetCustomerByCodeUseCaseImpl implements GetCustomerByCodeUseCase {
    private final CustomerRepository customerRepository;

    @Override
    public Customer execute(String code) throws CustomerNotFoundException {
        return customerRepository
                .findByCode(code)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with code: %s not found.", code)
                ));
    }
}
