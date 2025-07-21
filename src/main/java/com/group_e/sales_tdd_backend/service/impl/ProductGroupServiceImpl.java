package com.group_e.sales_tdd_backend.service.impl;

import com.group_e.sales_tdd_backend.dto.request.ProductGroupRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.ProductGroupResponse;
import com.group_e.sales_tdd_backend.entity.ProductGroup;
import com.group_e.sales_tdd_backend.exception.product_group.ProductGroupAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.product_group.ProductGroupNotFoundException;
import com.group_e.sales_tdd_backend.mapper.ProductGroupMapper;
import com.group_e.sales_tdd_backend.service.ProductGroupService;
import com.group_e.sales_tdd_backend.use_case.product_group.GetProductGroupByNameUseCase;
import com.group_e.sales_tdd_backend.use_case.product_group.GetProductGroupsUseCase;
import com.group_e.sales_tdd_backend.use_case.product_group.SaveProductGroupUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class ProductGroupServiceImpl implements ProductGroupService {
    private final GetProductGroupsUseCase getProductGroupsUseCase;
    private final GetProductGroupByNameUseCase getProductGroupByNameUseCase;
    private final SaveProductGroupUseCase saveProductGroupUseCase;
    private final ProductGroupMapper productGroupMapper;

    @Override
    public List<ProductGroupResponse> getProductGroups() {
        return productGroupMapper.toResponses(getProductGroupsUseCase.execute());
    }

    @Override
    public ProductGroupResponse registerProductGroup(ProductGroupRegistrationRequest request)
            throws ProductGroupAlreadyExistsException {
        String name = request.getName();
        try {
            getProductGroupByNameUseCase.execute(name.toUpperCase());
            throw new ProductGroupAlreadyExistsException(
                    String.format("A Product Group with the name: %s already exists.", name)
            );
        } catch (ProductGroupNotFoundException ignored) {
        }
        ProductGroup newProductGroup = new ProductGroup();
        newProductGroup.setName(name.toUpperCase());
        BigDecimal discountPercentage = request.getDiscountPercentage();
        if (discountPercentage != null) {
            newProductGroup.setDiscountPercentage(discountPercentage);
        }
        ProductGroup savedProductGroup = saveProductGroupUseCase.execute(newProductGroup);
        return productGroupMapper.toResponse(savedProductGroup);
    }
}
