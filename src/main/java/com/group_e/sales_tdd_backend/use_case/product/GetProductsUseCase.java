package com.group_e.sales_tdd_backend.use_case.product;

import com.group_e.sales_tdd_backend.entity.Product;

import java.util.List;

public interface GetProductsUseCase {
    List<Product> execute();
}
