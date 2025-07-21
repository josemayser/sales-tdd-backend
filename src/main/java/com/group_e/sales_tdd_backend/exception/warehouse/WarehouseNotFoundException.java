package com.group_e.sales_tdd_backend.exception.warehouse;

import com.group_e.sales_tdd_backend.exception.EntityNotFoundException;

public class WarehouseNotFoundException extends EntityNotFoundException {
    public WarehouseNotFoundException(String message) {
        super(message);
    }
}
