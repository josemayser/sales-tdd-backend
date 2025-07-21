package com.group_e.sales_tdd_backend.use_case.customer_group;

import com.group_e.sales_tdd_backend.entity.CustomerGroup;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupNotFoundException;

import java.util.UUID;

public interface GetCustomerGroupByIdUseCase {
    CustomerGroup execute(UUID id) throws CustomerGroupNotFoundException;
}
