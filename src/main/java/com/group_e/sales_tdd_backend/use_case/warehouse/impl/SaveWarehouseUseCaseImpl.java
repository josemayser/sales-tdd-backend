package com.group_e.sales_tdd_backend.use_case.warehouse.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Warehouse;
import com.group_e.sales_tdd_backend.repository.WarehouseRepository;
import com.group_e.sales_tdd_backend.use_case.warehouse.SaveWarehouseUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("saveWarehouseUseCase")
public class SaveWarehouseUseCaseImpl implements SaveWarehouseUseCase {
    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse execute(Warehouse warehouse) {
        return warehouseRepository.saveAndFlush(warehouse);
    }
}
