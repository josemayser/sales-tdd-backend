package com.group_e.sales_tdd_backend.use_case.product.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Product;
import com.group_e.sales_tdd_backend.repository.ProductRepository;
import com.group_e.sales_tdd_backend.use_case.product.GetProductsUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@UseCase("getProductsUseCase")
public class GetProductsUseCaseImpl implements GetProductsUseCase {
    private final ProductRepository productRepository;

    @Override
    public List<Product> execute() {
        return productRepository.findAll();
    }
}
