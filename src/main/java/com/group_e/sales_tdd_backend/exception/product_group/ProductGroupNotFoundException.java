package com.group_e.sales_tdd_backend.exception.product_group;

import com.group_e.sales_tdd_backend.exception.EntityNotFoundException;

public class ProductGroupNotFoundException extends EntityNotFoundException {
    public ProductGroupNotFoundException(String message) {
        super(message);
    }
}
