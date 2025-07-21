package com.group_e.sales_tdd_backend.use_case.product_group;

import com.group_e.sales_tdd_backend.entity.ProductGroup;
import com.group_e.sales_tdd_backend.exception.product_group.ProductGroupNotFoundException;

import java.util.UUID;

public interface GetProductGroupByIdUseCase {
    ProductGroup execute(UUID id) throws ProductGroupNotFoundException;
}
