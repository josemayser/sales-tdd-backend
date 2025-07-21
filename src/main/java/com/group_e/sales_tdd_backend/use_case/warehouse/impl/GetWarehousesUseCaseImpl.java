package com.group_e.sales_tdd_backend.use_case.warehouse.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Warehouse;
import com.group_e.sales_tdd_backend.repository.WarehouseRepository;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehousesUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@UseCase("getWarehousesUseCase")
public class GetWarehousesUseCaseImpl implements GetWarehousesUseCase {
    private final WarehouseRepository warehouseRepository;

    @Override
    public List<Warehouse> execute() {
        return warehouseRepository.findAll();
    }
}
