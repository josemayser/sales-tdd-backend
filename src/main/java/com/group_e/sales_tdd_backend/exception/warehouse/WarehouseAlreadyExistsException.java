package com.group_e.sales_tdd_backend.exception.warehouse;

import com.group_e.sales_tdd_backend.exception.EntityAlreadyExistsException;

public class WarehouseAlreadyExistsException extends EntityAlreadyExistsException {
    public WarehouseAlreadyExistsException(String message) {
        super(message);
    }
}
