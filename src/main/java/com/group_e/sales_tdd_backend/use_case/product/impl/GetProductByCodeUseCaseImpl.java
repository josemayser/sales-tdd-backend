package com.group_e.sales_tdd_backend.use_case.product.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Product;
import com.group_e.sales_tdd_backend.exception.product.ProductNotFoundException;
import com.group_e.sales_tdd_backend.repository.ProductRepository;
import com.group_e.sales_tdd_backend.use_case.product.GetProductByCodeUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("getProductByCodeUseCase")
public class GetProductByCodeUseCaseImpl implements GetProductByCodeUseCase {
    private final ProductRepository productRepository;

    @Override
    public Product execute(String code) throws ProductNotFoundException {
        return productRepository
                .findByCode(code)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product with code: %s not found.", code)
                ));
    }
}
