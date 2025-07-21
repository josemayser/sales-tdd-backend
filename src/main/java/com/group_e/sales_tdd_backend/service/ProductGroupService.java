package com.group_e.sales_tdd_backend.service;

import com.group_e.sales_tdd_backend.dto.request.ProductGroupRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.ProductGroupResponse;
import com.group_e.sales_tdd_backend.exception.product_group.ProductGroupAlreadyExistsException;

import java.util.List;

public interface ProductGroupService {
    List<ProductGroupResponse> getProductGroups();

    ProductGroupResponse registerProductGroup(ProductGroupRegistrationRequest request)
            throws ProductGroupAlreadyExistsException;
}
