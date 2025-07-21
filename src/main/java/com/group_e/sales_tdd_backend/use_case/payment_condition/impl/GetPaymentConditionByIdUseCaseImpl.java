package com.group_e.sales_tdd_backend.use_case.payment_condition.impl;

import com.group_e.sales_tdd_backend.annotations.UseCase;
import com.group_e.sales_tdd_backend.entity.PaymentCondition;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionNotFoundException;
import com.group_e.sales_tdd_backend.repository.PaymentConditionRepository;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionByIdUseCase;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@UseCase("getPaymentConditionByIdUseCase")
public class GetPaymentConditionByIdUseCaseImpl implements GetPaymentConditionByIdUseCase {
    private final PaymentConditionRepository paymentConditionRepository;

    @Override
    public PaymentCondition execute(UUID id) throws PaymentConditionNotFoundException {
        return paymentConditionRepository.findById(id).orElseThrow(() -> new PaymentConditionNotFoundException(
                String.format("Payment Condition with id: %s not found.", id)
        ));
    }
}
