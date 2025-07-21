package com.group_e.sales_tdd_backend.service;

import com.group_e.sales_tdd_backend.dto.request.ProductRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.ProductResponse;
import com.group_e.sales_tdd_backend.exception.product.ProductAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.product_group.ProductGroupNotFoundException;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getProducts();

    ProductResponse registerProduct(ProductRegistrationRequest request) throws ProductGroupNotFoundException,
            ProductAlreadyExistsException;
}
