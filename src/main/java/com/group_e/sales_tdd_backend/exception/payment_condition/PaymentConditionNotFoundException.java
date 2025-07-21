package com.group_e.sales_tdd_backend.exception.payment_condition;

import com.group_e.sales_tdd_backend.exception.EntityNotFoundException;

public class PaymentConditionNotFoundException extends EntityNotFoundException {
    public PaymentConditionNotFoundException(String message) {
        super(message);
    }
}
