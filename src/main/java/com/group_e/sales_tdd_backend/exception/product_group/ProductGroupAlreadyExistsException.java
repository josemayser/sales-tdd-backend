package com.group_e.sales_tdd_backend.exception.product_group;

import com.group_e.sales_tdd_backend.exception.EntityAlreadyExistsException;

public class ProductGroupAlreadyExistsException extends EntityAlreadyExistsException {
    public ProductGroupAlreadyExistsException(String message) {
        super(message);
    }
}
