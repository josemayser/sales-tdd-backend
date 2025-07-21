package com.group_e.sales_tdd_backend.exception.customer_group;

import com.group_e.sales_tdd_backend.exception.EntityNotFoundException;

public class CustomerGroupNotFoundException extends EntityNotFoundException {
    public CustomerGroupNotFoundException(String message) {
        super(message);
    }
}
