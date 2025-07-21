package com.group_e.sales_tdd_backend.use_case.product_group.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.ProductGroup;
import com.group_e.sales_tdd_backend.exception.product_group.ProductGroupNotFoundException;
import com.group_e.sales_tdd_backend.repository.ProductGroupRepository;
import com.group_e.sales_tdd_backend.use_case.product_group.GetProductGroupByNameUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("getProductGroupByNameUseCase")
public class GetProductGroupByNameUseCaseImpl implements GetProductGroupByNameUseCase {
    private final ProductGroupRepository productGroupRepository;

    @Override
    public ProductGroup execute(String name) throws ProductGroupNotFoundException {
        return productGroupRepository
                .findByName(name)
                .orElseThrow(() -> new ProductGroupNotFoundException(
                        String.format("Product Group with name: %s not found.", name)
                ));
    }
}
