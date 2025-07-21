package com.group_e.sales_tdd_backend.use_case.customer_group.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.CustomerGroup;
import com.group_e.sales_tdd_backend.repository.CustomerGroupRepository;
import com.group_e.sales_tdd_backend.use_case.customer_group.SaveCustomerGroupUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("saveCustomerGroupUseCase")
public class SaveCustomerGroupUseCaseImpl implements SaveCustomerGroupUseCase {
    private CustomerGroupRepository customerGroupRepository;

    @Override
    public CustomerGroup execute(CustomerGroup customerGroup) {
        return customerGroupRepository.saveAndFlush(customerGroup);
    }
}
