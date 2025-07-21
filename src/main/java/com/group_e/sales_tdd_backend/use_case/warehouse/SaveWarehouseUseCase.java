package com.group_e.sales_tdd_backend.use_case.warehouse;

import com.group_e.sales_tdd_backend.entity.Warehouse;

public interface SaveWarehouseUseCase {
    Warehouse execute(Warehouse warehouse);
}
