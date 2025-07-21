package com.group_e.sales_tdd_backend.use_case.warehouse.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Warehouse;
import com.group_e.sales_tdd_backend.exception.warehouse.WarehouseNotFoundException;
import com.group_e.sales_tdd_backend.repository.WarehouseRepository;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehouseByIdUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@UseCase("getWarehouseByIdUseCase")
public class GetWarehouseByIdUseCaseImpl implements GetWarehouseByIdUseCase {
    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse execute(UUID id) throws WarehouseNotFoundException {
        return warehouseRepository.findById(id).orElseThrow(() -> new WarehouseNotFoundException(
                String.format("Warehouse with id: %s not found.", id)
        ));
    }
}
