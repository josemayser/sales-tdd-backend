package com.group_e.sales_tdd_backend.use_case.payment_condition.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.PaymentCondition;
import com.group_e.sales_tdd_backend.repository.PaymentConditionRepository;
import com.group_e.sales_tdd_backend.use_case.payment_condition.SavePaymentConditionUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("savePaymentConditionUseCase")
public class SavePaymentConditionUseCaseImpl implements SavePaymentConditionUseCase {
    private final PaymentConditionRepository paymentConditionRepository;

    @Override
    public PaymentCondition execute(PaymentCondition paymentCondition) {
        return paymentConditionRepository.saveAndFlush(paymentCondition);
    }
}
