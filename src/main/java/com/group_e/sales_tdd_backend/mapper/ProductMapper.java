package com.group_e.sales_tdd_backend.mapper;

import com.group_e.sales_tdd_backend.dto.response.ProductResponse;
import com.group_e.sales_tdd_backend.entity.Product;
import org.mapstruct.Mapper;

@Mapper(uses = {ProductGroupMapper.class})
public interface ProductMapper {
    ProductResponse toResponse(Product product);
}
