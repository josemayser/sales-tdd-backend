package com.group_e.sales_tdd_backend.controller;

import com.group_e.sales_tdd_backend.dto.request.WarehouseRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.WarehouseResponse;
import com.group_e.sales_tdd_backend.exception.warehouse.WarehouseAlreadyExistsException;
import com.group_e.sales_tdd_backend.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;

    @GetMapping
    public List<WarehouseResponse> getCustomers() {
        return warehouseService.getWarehouses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WarehouseResponse registerWarehouse(@Valid @RequestBody WarehouseRegistrationRequest request)
            throws WarehouseAlreadyExistsException {
        return warehouseService.registerWarehouse(request);
    }
}
