package com.group_e.sales_tdd_backend.mapper;

import com.group_e.sales_tdd_backend.dto.response.WarehouseResponse;
import com.group_e.sales_tdd_backend.entity.Warehouse;
import org.mapstruct.Mapper;

@Mapper
public interface WarehouseMapper {
    WarehouseResponse toResponse(Warehouse warehouse);
}
