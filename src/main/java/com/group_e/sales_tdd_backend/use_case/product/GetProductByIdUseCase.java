package com.group_e.sales_tdd_backend.use_case.product;

import com.group_e.sales_tdd_backend.entity.Product;
import com.group_e.sales_tdd_backend.exception.product.ProductNotFoundException;

import java.util.UUID;

public interface GetProductByIdUseCase {
    Product execute(UUID id) throws ProductNotFoundException;
}
