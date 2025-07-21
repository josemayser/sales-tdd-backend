package com.group_e.sales_tdd_backend.use_case.product_group.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.ProductGroup;
import com.group_e.sales_tdd_backend.repository.ProductGroupRepository;
import com.group_e.sales_tdd_backend.use_case.product_group.GetProductGroupsUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@UseCase("getProductGroupsUseCase")
public class GetProductGroupsUseCaseImpl implements GetProductGroupsUseCase {
    private final ProductGroupRepository productGroupRepository;

    @Override
    public List<ProductGroup> execute() {
        return productGroupRepository.findAll();
    }
}
