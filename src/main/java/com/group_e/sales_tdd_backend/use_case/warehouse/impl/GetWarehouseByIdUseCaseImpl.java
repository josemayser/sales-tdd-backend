package com.group_e.sales_tdd_backend.use_case.warehouse.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.Warehouse;
import com.group_e.sales_tdd_backend.repository.WarehouseRepository;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehouseByIdUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@UseCase("getWarehouseByIdUseCase")
public class GetWarehouseByIdUseCaseImpl implements GetWarehouseByIdUseCase {
    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse execute(UUID id) {
        return warehouseRepository.findById(id).orElse(null);
    }
}
