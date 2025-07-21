package com.group_e.sales_tdd_backend.exception.product;

import com.group_e.sales_tdd_backend.exception.EntityAlreadyExistsException;

public class ProductAlreadyExistsException extends EntityAlreadyExistsException {
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
