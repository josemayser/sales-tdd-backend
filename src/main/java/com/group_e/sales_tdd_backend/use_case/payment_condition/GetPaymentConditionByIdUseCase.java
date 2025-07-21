package com.group_e.sales_tdd_backend.use_case.payment_condition;

import com.group_e.sales_tdd_backend.entity.PaymentCondition;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionNotFoundException;

import java.util.UUID;

public interface GetPaymentConditionByIdUseCase {
    PaymentCondition execute(UUID id) throws PaymentConditionNotFoundException;
}
