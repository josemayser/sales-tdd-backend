package com.group_e.sales_tdd_backend.use_case.payment_condition;

import com.group_e.sales_tdd_backend.entity.PaymentCondition;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionNotFoundException;

public interface GetPaymentConditionByNameUseCase {
    PaymentCondition execute(String name) throws PaymentConditionNotFoundException;
}
