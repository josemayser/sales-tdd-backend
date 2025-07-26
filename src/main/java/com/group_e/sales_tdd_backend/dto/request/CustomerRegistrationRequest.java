package com.group_e.sales_tdd_backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CustomerRegistrationRequest {
    @NotNull(message = "The customer group id is required")
    private UUID customerGroupId;

    @NotBlank(message = "The name is required")
    private String name;

    @NotBlank(message = "The code is required")
    private String code;

    @NotBlank(message = "The dni is required")
    private String dni;

    @Email(message = "The email must be a valid email address")
    private String email;
}
