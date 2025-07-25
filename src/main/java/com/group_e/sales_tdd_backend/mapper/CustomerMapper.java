package com.group_e.sales_tdd_backend.mapper;

import com.group_e.sales_tdd_backend.dto.response.CustomerResponse;
import com.group_e.sales_tdd_backend.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {CustomerGroupMapper.class})
public interface CustomerMapper {
    CustomerResponse toResponse(Customer customer);

    List<CustomerResponse> toResponses(List<Customer> customers);
}
