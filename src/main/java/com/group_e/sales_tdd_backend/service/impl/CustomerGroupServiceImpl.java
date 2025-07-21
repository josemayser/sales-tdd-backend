package com.group_e.sales_tdd_backend.service.impl;

import com.group_e.sales_tdd_backend.dto.request.CustomerGroupRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.CustomerGroupResponse;
import com.group_e.sales_tdd_backend.entity.CustomerGroup;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.customer_group.CustomerGroupNotFoundException;
import com.group_e.sales_tdd_backend.mapper.CustomerGroupMapper;
import com.group_e.sales_tdd_backend.service.CustomerGroupService;
import com.group_e.sales_tdd_backend.use_case.customer_group.GetCustomerGroupByNameUseCase;
import com.group_e.sales_tdd_backend.use_case.customer_group.GetCustomerGroupsUseCase;
import com.group_e.sales_tdd_backend.use_case.customer_group.SaveCustomerGroupUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {
    private final GetCustomerGroupsUseCase getCustomerGroupsUseCase;
    private final GetCustomerGroupByNameUseCase getCustomerGroupByNameUseCase;
    private final SaveCustomerGroupUseCase saveCustomerGroupUseCase;
    private final CustomerGroupMapper customerGroupMapper;

    @Override
    public List<CustomerGroupResponse> getCustomerGroups() {
        return customerGroupMapper.toResponses(getCustomerGroupsUseCase.execute());
    }

    @Override
    public CustomerGroupResponse registerCustomerGroup(CustomerGroupRegistrationRequest request)
            throws CustomerGroupAlreadyExistsException {
        String name = request.getName();
        try {
            getCustomerGroupByNameUseCase.execute(name.toUpperCase());
            throw new CustomerGroupAlreadyExistsException(
                    String.format("A Customer Group with the name: %s already exists.", name)
            );
        } catch (CustomerGroupNotFoundException ignored) {
        }
        CustomerGroup newCustomerGroup = new CustomerGroup();
        newCustomerGroup.setName(name.toUpperCase());
        BigDecimal discountPercentage = request.getDiscountPercentage();
        if (discountPercentage != null) {
            newCustomerGroup.setDiscountPercentage(discountPercentage);
        }
        CustomerGroup savedCustomerGroup = saveCustomerGroupUseCase.execute(newCustomerGroup);
        return customerGroupMapper.toResponse(savedCustomerGroup);
    }
}
