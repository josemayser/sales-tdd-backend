package com.group_e.sales_tdd_backend.use_case.warehouse;

import com.group_e.sales_tdd_backend.entity.Warehouse;

import java.util.UUID;

public interface GetWarehouseByIdUseCase {
    Warehouse execute(UUID id);
}
