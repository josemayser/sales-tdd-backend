package com.group_e.sales_tdd_backend.mapper;

import com.group_e.sales_tdd_backend.dto.response.ProductGroupResponse;
import com.group_e.sales_tdd_backend.entity.ProductGroup;
import org.mapstruct.Mapper;

@Mapper
public interface ProductGroupMapper {
    ProductGroupResponse toResponse(ProductGroup productGroup);
}
