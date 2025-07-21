package com.group_e.sales_tdd_backend.use_case.product_group.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.ProductGroup;
import com.group_e.sales_tdd_backend.repository.ProductGroupRepository;
import com.group_e.sales_tdd_backend.use_case.product_group.SaveProductGroupUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("saveProductGroupUseCase")
public class SaveProductGroupUseCaseImpl implements SaveProductGroupUseCase {
    private final ProductGroupRepository productGroupRepository;

    @Override
    public ProductGroup execute(ProductGroup productGroup) {
        return productGroupRepository.saveAndFlush(productGroup);
    }
}
