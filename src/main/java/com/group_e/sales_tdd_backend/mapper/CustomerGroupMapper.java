package com.group_e.sales_tdd_backend.mapper;

import com.group_e.sales_tdd_backend.dto.response.CustomerGroupResponse;
import com.group_e.sales_tdd_backend.entity.CustomerGroup;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerGroupMapper {
    CustomerGroupResponse toResponse(CustomerGroup customerGroup);
}
