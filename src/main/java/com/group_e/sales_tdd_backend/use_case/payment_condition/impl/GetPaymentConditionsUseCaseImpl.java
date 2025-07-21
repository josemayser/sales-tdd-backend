package com.group_e.sales_tdd_backend.use_case.payment_condition.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.PaymentCondition;
import com.group_e.sales_tdd_backend.repository.PaymentConditionRepository;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionsUseCase;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@UseCase("getPaymentConditionsUseCase")
public class GetPaymentConditionsUseCaseImpl implements GetPaymentConditionsUseCase {
    private final PaymentConditionRepository paymentConditionRepository;

    @Override
    public List<PaymentCondition> execute() {
        return paymentConditionRepository.findAll();
    }
}
