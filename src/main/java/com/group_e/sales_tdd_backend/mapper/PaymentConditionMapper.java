package com.group_e.sales_tdd_backend.mapper;

import com.group_e.sales_tdd_backend.dto.response.PaymentConditionResponse;
import com.group_e.sales_tdd_backend.entity.PaymentCondition;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface PaymentConditionMapper {
    PaymentConditionResponse toResponse(PaymentCondition paymentCondition);

    List<PaymentConditionResponse> toResponses(List<PaymentCondition> paymentConditions);
}
