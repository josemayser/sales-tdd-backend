package com.group_e.sales_tdd_backend.use_case.product;

import com.group_e.sales_tdd_backend.entity.Product;
import com.group_e.sales_tdd_backend.exception.product.ProductNotFoundException;

public interface GetProductByCodeUseCase {
    Product execute(String code) throws ProductNotFoundException;
}
