package com.group_e.sales_tdd_backend.use_case.payment_condition.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.PaymentCondition;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionNotFoundException;
import com.group_e.sales_tdd_backend.repository.PaymentConditionRepository;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionByNameUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase("getPaymentConditionByNameUseCase")
public class GetPaymentConditionByNameUseCaseImpl implements GetPaymentConditionByNameUseCase {
    private final PaymentConditionRepository paymentConditionRepository;

    @Override
    public PaymentCondition execute(String name) throws PaymentConditionNotFoundException {
        return paymentConditionRepository
                .findByName(name)
                .orElseThrow(() -> new PaymentConditionNotFoundException(
                        String.format("Payment Condition with name: %s not found.", name)
                ));
    }
}
