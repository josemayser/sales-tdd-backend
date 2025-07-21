package com.group_e.sales_tdd_backend.use_case.product;

import com.group_e.sales_tdd_backend.entity.Product;

public interface SaveProductUseCase {
    Product execute(Product product);
}
