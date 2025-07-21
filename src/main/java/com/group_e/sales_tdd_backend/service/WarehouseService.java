package com.group_e.sales_tdd_backend.service;

import com.group_e.sales_tdd_backend.dto.request.WarehouseRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.WarehouseResponse;
import com.group_e.sales_tdd_backend.exception.warehouse.WarehouseAlreadyExistsException;

import java.util.List;

public interface WarehouseService {
    List<WarehouseResponse> getWarehouses();

    WarehouseResponse registerWarehouse(WarehouseRegistrationRequest request) throws WarehouseAlreadyExistsException;
}
