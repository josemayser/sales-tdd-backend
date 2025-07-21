package com.group_e.sales_tdd_backend.use_case.product_group;

import com.group_e.sales_tdd_backend.entity.ProductGroup;

import java.util.List;

public interface GetProductGroupsUseCase {
    List<ProductGroup> execute();
}
