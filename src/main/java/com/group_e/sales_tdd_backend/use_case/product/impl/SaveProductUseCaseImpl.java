package com.group_e.sales_tdd_backend.use_case.product.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Product;
import com.group_e.sales_tdd_backend.repository.ProductRepository;
import com.group_e.sales_tdd_backend.use_case.product.SaveProductUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("saveProductUseCase")
public class SaveProductUseCaseImpl implements SaveProductUseCase {
    private final ProductRepository productRepository;

    @Override
    public Product execute(Product product) {
        return productRepository.saveAndFlush(product);
    }
}
