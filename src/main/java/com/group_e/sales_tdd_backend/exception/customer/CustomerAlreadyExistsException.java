package com.group_e.sales_tdd_backend.exception.customer;

import com.group_e.sales_tdd_backend.exception.EntityAlreadyExistsException;

public class CustomerAlreadyExistsException extends EntityAlreadyExistsException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
