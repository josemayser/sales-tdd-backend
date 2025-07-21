package com.group_e.sales_tdd_backend.use_case.payment_condition;

import com.group_e.sales_tdd_backend.entity.PaymentCondition;

public interface SavePaymentConditionUseCase {
    PaymentCondition execute(PaymentCondition paymentCondition);
}
