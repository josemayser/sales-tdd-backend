package com.group_e.sales_tdd_backend.use_case.warehouse;

import com.group_e.sales_tdd_backend.entity.Warehouse;
import com.group_e.sales_tdd_backend.exception.warehouse.WarehouseNotFoundException;

public interface GetWarehouseByNameUseCase {
    Warehouse execute(String name) throws WarehouseNotFoundException;
}
