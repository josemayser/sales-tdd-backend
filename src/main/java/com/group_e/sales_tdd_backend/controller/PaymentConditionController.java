package com.group_e.sales_tdd_backend.controller;

import com.group_e.sales_tdd_backend.dto.request.PaymentConditionRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.PaymentConditionResponse;
import com.group_e.sales_tdd_backend.exception.payment_condition.PaymentConditionAlreadyExistsException;
import com.group_e.sales_tdd_backend.service.PaymentConditionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/payment-conditions")
public class PaymentConditionController {
    private final PaymentConditionService paymentConditionService;

    @GetMapping
    public List<PaymentConditionResponse> getPaymentConditions() {
        return paymentConditionService.getPaymentConditions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentConditionResponse registerPaymentCondition(
            @Valid @RequestBody PaymentConditionRegistrationRequest request
    ) throws PaymentConditionAlreadyExistsException {
        return paymentConditionService.registerPaymentCondition(request);
    }
}
