package com.group_e.sales_tdd_backend.service.impl;

import com.group_e.sales_tdd_backend.dto.request.WarehouseRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.WarehouseResponse;
import com.group_e.sales_tdd_backend.entity.Warehouse;
import com.group_e.sales_tdd_backend.exception.warehouse.WarehouseAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.warehouse.WarehouseNotFoundException;
import com.group_e.sales_tdd_backend.mapper.WarehouseMapper;
import com.group_e.sales_tdd_backend.service.WarehouseService;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehouseByNameUseCase;
import com.group_e.sales_tdd_backend.use_case.warehouse.GetWarehousesUseCase;
import com.group_e.sales_tdd_backend.use_case.warehouse.SaveWarehouseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final GetWarehousesUseCase getWarehousesUseCase;
    private final GetWarehouseByNameUseCase getWarehouseByNameUseCase;
    private final SaveWarehouseUseCase saveWarehouseUseCase;
    private final WarehouseMapper warehouseMapper;

    @Override
    public List<WarehouseResponse> getWarehouses() {
        return warehouseMapper.toResponses(getWarehousesUseCase.execute());
    }

    @Override
    public WarehouseResponse registerWarehouse(WarehouseRegistrationRequest request)
            throws WarehouseAlreadyExistsException {
        String name = request.getName();
        try {
            getWarehouseByNameUseCase.execute(name.toUpperCase());
            throw new WarehouseAlreadyExistsException(
                    String.format("A Warehouse with the name: %s already exists.", name)
            );
        } catch (WarehouseNotFoundException ignored) {
        }
        Warehouse newWarehouse = new Warehouse();
        newWarehouse.setName(name.toUpperCase());
        newWarehouse.setLocation(request.getLocation());
        Warehouse savedWarehouse = saveWarehouseUseCase.execute(newWarehouse);
        return warehouseMapper.toResponse(savedWarehouse);
    }
}
