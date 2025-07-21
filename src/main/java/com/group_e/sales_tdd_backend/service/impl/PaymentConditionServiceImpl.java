package com.group_e.sales_tdd_backend.service.impl;

import com.group_e.sales_tdd_backend.dto.request.PaymentConditionRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.PaymentConditionResponse;
import com.group_e.sales_tdd_backend.entity.PaymentCondition;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionNotFoundException;
import com.group_e.sales_tdd_backend.mapper.PaymentConditionMapper;
import com.group_e.sales_tdd_backend.service.PaymentConditionService;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionByNameUseCase;
import com.group_e.sales_tdd_backend.use_case.payment_condition.GetPaymentConditionsUseCase;
import com.group_e.sales_tdd_backend.use_case.payment_condition.SavePaymentConditionUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PaymentConditionServiceImpl implements PaymentConditionService {
    private final GetPaymentConditionsUseCase getPaymentConditionsUseCase;
    private final GetPaymentConditionByNameUseCase getPaymentConditionByNameUseCase;
    private final SavePaymentConditionUseCase savePaymentConditionUseCase;
    private final PaymentConditionMapper paymentConditionMapper;

    @Override
    public List<PaymentConditionResponse> getPaymentConditions() {
        return paymentConditionMapper.toResponses(getPaymentConditionsUseCase.execute());
    }

    @Override
    public PaymentConditionResponse registerPaymentCondition(PaymentConditionRegistrationRequest request)
            throws PaymentConditionAlreadyExistsException {
        String name = request.getName();
        try {
            getPaymentConditionByNameUseCase.execute(name.toUpperCase());
            throw new PaymentConditionAlreadyExistsException(
                    String.format("A Payment Condition with the name: %s already exists.", name)
            );
        } catch (PaymentConditionNotFoundException ignored) {
        }
        PaymentCondition newPaymentCondition = new PaymentCondition();
        newPaymentCondition.setName(name.toUpperCase());
        PaymentCondition savedPaymentCondition = savePaymentConditionUseCase.execute(newPaymentCondition);
        return paymentConditionMapper.toResponse(savedPaymentCondition);
    }
}
