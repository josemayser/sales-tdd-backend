package com.group_e.sales_tdd_backend.use_case.product_group;

import com.group_e.sales_tdd_backend.entity.ProductGroup;

public interface SaveProductGroupUseCase {
    ProductGroup execute(ProductGroup productGroup);
}
