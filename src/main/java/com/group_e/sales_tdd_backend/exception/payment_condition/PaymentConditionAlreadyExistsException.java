package com.group_e.sales_tdd_backend.exception.payment_condition;

import com.group_e.sales_tdd_backend.exception.EntityAlreadyExistsException;

public class PaymentConditionAlreadyExistsException extends EntityAlreadyExistsException {
    public PaymentConditionAlreadyExistsException(String message) {
        super(message);
    }
}
