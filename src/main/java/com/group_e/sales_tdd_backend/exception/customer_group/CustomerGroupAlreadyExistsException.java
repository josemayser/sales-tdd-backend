package com.group_e.sales_tdd_backend.exception.customer_group;

import com.group_e.sales_tdd_backend.exception.EntityAlreadyExistsException;

public class CustomerGroupAlreadyExistsException extends EntityAlreadyExistsException {
    public CustomerGroupAlreadyExistsException(String message) {
        super(message);
    }
}
