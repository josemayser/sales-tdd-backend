package com.group_e.sales_tdd_backend.use_case.customer_group;

import com.group_e.sales_tdd_backend.entity.CustomerGroup;

public interface SaveCustomerGroupUseCase {
    CustomerGroup execute(CustomerGroup customerGroup);
}
