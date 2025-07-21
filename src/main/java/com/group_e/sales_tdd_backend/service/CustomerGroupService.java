package com.group_e.sales_tdd_backend.service;

import com.group_e.sales_tdd_backend.dto.request.CustomerGroupRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.CustomerGroupResponse;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupAlreadyExistsException;

import java.util.List;

public interface CustomerGroupService {
    List<CustomerGroupResponse> getCustomerGroups();

    CustomerGroupResponse registerCustomerGroup(CustomerGroupRegistrationRequest request)
            throws CustomerGroupAlreadyExistsException;
}
