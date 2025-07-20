package com.group_e.sales_tdd_backend.entity;

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
public class Customer {
    private UUID id;
    private UUID customerGroupId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String name;
    private String code;
    private CustomerGroup customerGroup;
}
