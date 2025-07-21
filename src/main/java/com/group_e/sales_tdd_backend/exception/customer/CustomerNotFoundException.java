package com.group_e.sales_tdd_backend.exception.customer;

import com.group_e.sales_tdd_backend.exception.EntityNotFoundException;

public class CustomerNotFoundException extends EntityNotFoundException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
