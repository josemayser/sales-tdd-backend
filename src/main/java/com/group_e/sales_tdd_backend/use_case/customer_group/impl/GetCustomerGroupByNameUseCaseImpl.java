package com.group_e.sales_tdd_backend.use_case.customer_group.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.CustomerGroup;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupNotFoundException;
import com.group_e.sales_tdd_backend.repository.CustomerGroupRepository;
import com.group_e.sales_tdd_backend.use_case.customer_group.GetCustomerGroupByNameUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("getCustomerGroupByNameUseCase")
public class GetCustomerGroupByNameUseCaseImpl implements GetCustomerGroupByNameUseCase {
    private final CustomerGroupRepository customerGroupRepository;

    @Override
    public CustomerGroup execute(String name) throws CustomerGroupNotFoundException {
        return customerGroupRepository
                .findByName(name)
                .orElseThrow(() -> new CustomerGroupNotFoundException(
                        String.format("Customer Group with name: %s not found.", name)
                ));
    }
}
