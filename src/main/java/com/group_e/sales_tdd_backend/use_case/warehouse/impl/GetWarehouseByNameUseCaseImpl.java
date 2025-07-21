package com.group_e.sales_tdd_backend.use_case.warehouse.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Warehouse;
import com.group_e.sales_tdd_backend.exception.warehouse.WarehouseNotFoundException;
import com.group_e.sales_tdd_backend.repository.WarehouseRepository;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehouseByNameUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("getWarehouseByNameUseCase")
public class GetWarehouseByNameUseCaseImpl implements GetWarehouseByNameUseCase {
    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse execute(String name) throws WarehouseNotFoundException {
        return warehouseRepository
                .findByName(name)
                .orElseThrow(() -> new WarehouseNotFoundException(
                        String.format("Warehouse with name: %s not found.", name)
                ));
    }
}
