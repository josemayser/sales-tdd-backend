package com.group_e.sales_tdd_backend.use_case.product.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Product;
import com.group_e.sales_tdd_backend.exception.product.ProductNotFoundException;
import com.group_e.sales_tdd_backend.repository.ProductRepository;
import com.group_e.sales_tdd_backend.use_case.product.GetProductByIdUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@UseCase("getProductByIdUseCase")
public class GetProductByIdUseCaseImpl implements GetProductByIdUseCase {
    private final ProductRepository productRepository;

    @Override
    public Product execute(UUID id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(
                String.format("Product with id: %s not found.", id)
        ));
    }
}
