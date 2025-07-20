package com.group_e.sales_tdd_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentConditionResponse {
    private UUID id;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String name;
}
