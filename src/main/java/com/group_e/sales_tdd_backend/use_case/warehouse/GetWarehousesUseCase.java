package com.group_e.sales_tdd_backend.use_case.warehouse;

import com.group_e.sales_tdd_backend.entity.Warehouse;

import java.util.List;

public interface GetWarehousesUseCase {
    List<Warehouse> execute();
}
