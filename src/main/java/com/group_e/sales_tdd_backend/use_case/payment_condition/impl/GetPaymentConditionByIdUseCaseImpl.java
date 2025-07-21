package com.group_e.sales_tdd_backend.use_case.payment_condition.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.PaymentCondition;
import com.group_e.sales_tdd_backend.repository.PaymentConditionRepository;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionByIdUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@UseCase("getPaymentConditionByIdUseCase")
public class GetPaymentConditionByIdUseCaseImpl implements GetPaymentConditionByIdUseCase {
    private final PaymentConditionRepository paymentConditionRepository;

    @Override
    public PaymentCondition execute(UUID id) {
        return paymentConditionRepository.findById(id).orElse(null);
    }
}
