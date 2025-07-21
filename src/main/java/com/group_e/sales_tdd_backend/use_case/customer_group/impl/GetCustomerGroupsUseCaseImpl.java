package com.group_e.sales_tdd_backend.use_case.customer_group.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.CustomerGroup;
import com.group_e.sales_tdd_backend.repository.CustomerGroupRepository;
import com.group_e.sales_tdd_backend.use_case.customer_group.GetCustomerGroupsUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@UseCase("getCustomerGroupsUseCase")
public class GetCustomerGroupsUseCaseImpl implements GetCustomerGroupsUseCase {
    private final CustomerGroupRepository customerGroupRepository;

    @Override
    public List<CustomerGroup> execute() {
        return customerGroupRepository.findAll();
    }
}
