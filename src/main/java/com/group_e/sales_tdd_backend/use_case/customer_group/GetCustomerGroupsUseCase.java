package com.group_e.sales_tdd_backend.use_case.customer_group;

import com.group_e.sales_tdd_backend.entity.CustomerGroup;

import java.util.List;

public interface GetCustomerGroupsUseCase {
    List<CustomerGroup> execute();
}
