package com.group_e.sales_tdd_backend.use_case.product_group.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.ProductGroup;
import com.group_e.sales_tdd_backend.exception.product_group.ProductGroupNotFoundException;
import com.group_e.sales_tdd_backend.repository.ProductGroupRepository;
import com.group_e.sales_tdd_backend.use_case.product_group.GetProductGroupByIdUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@UseCase("getProductGroupByIdUseCase")
public class GetProductGroupByIdUseCaseImpl implements GetProductGroupByIdUseCase {
    private final ProductGroupRepository productGroupRepository;

    @Override
    public ProductGroup execute(UUID id) throws ProductGroupNotFoundException {
        return productGroupRepository.findById(id).orElseThrow(() -> new ProductGroupNotFoundException(
                String.format("Product Group with id: %s not found.", id)
        ));
    }
}
