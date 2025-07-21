package com.group_e.sales_tdd_backend.use_case.customer_group.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.CustomerGroup;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupNotFoundException;
import com.group_e.sales_tdd_backend.repository.CustomerGroupRepository;
import com.group_e.sales_tdd_backend.use_case.customer_group.GetCustomerGroupByIdUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@UseCase("getCustomerGroupByIdUseCase")
public class GetCustomerGroupByIdUseCaseImpl implements GetCustomerGroupByIdUseCase {
    private final CustomerGroupRepository customerGroupRepository;

    @Override
    public CustomerGroup execute(UUID id) throws CustomerGroupNotFoundException {
        return customerGroupRepository.findById(id).orElseThrow(() -> new CustomerGroupNotFoundException(
                String.format("Customer Group with id: %s not found.", id)
        ));
    }
}
