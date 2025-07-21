package com.group_e.sales_tdd_backend.controller;

import com.group_e.sales_tdd_backend.dto.request.ProductGroupRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.request.ProductRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.ProductGroupResponse;
import com.group_e.sales_tdd_backend.dto.response.ProductResponse;
import com.group_e.sales_tdd_backend.exception.product.ProductAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.product_group.ProductGroupAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.product_group.ProductGroupNotFoundException;
import com.group_e.sales_tdd_backend.service.ProductGroupService;
import com.group_e.sales_tdd_backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductGroupService productGroupService;
    private final ProductService productService;

    @GetMapping("/groups")
    public List<ProductGroupResponse> getProductGroups() {
        return productGroupService.getProductGroups();
    }

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductGroupResponse registerProductGroup(@Valid @RequestBody ProductGroupRegistrationRequest request)
            throws ProductGroupAlreadyExistsException {
        return productGroupService.registerProductGroup(request);
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse registerProduct(@Valid @RequestBody ProductRegistrationRequest request)
            throws ProductGroupNotFoundException, ProductAlreadyExistsException {
        return productService.registerProduct(request);
    }
}
