package com.group_e.sales_tdd_backend.exception.product;

import com.group_e.sales_tdd_backend.exception.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
