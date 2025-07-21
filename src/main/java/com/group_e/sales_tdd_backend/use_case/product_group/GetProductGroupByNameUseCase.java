package com.group_e.sales_tdd_backend.use_case.product_group;

import com.group_e.sales_tdd_backend.entity.ProductGroup;
import com.group_e.sales_tdd_backend.exception.product_group.ProductGroupNotFoundException;

public interface GetProductGroupByNameUseCase {
    ProductGroup execute(String name) throws ProductGroupNotFoundException;
}
