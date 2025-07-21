package com.group_e.sales_tdd_backend.service;

import com.group_e.sales_tdd_backend.dto.request.PaymentConditionRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.PaymentConditionResponse;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionAlreadyExistsException;

import java.util.List;

public interface PaymentConditionService {
    List<PaymentConditionResponse> getPaymentConditions();

    PaymentConditionResponse registerPaymentCondition(PaymentConditionRegistrationRequest request)
            throws PaymentConditionAlreadyExistsException;
}
