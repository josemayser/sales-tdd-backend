package com.group_e.sales_tdd_backend.use_case.customer_group;

import com.group_e.sales_tdd_backend.entity.CustomerGroup;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupNotFoundException;

public interface GetCustomerGroupByNameUseCase {
    CustomerGroup execute(String name) throws CustomerGroupNotFoundException;
}
