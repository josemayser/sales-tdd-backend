package com.group_e.sales_tdd_backend.service.impl;

import com.group_e.sales_tdd_backend.dto.request.ProductRegistrationRequest;
import com.group_e.sales_tdd_backend.dto.response.ProductResponse;
import com.group_e.sales_tdd_backend.entity.Product;
import com.group_e.sales_tdd_backend.entity.ProductGroup;
import com.group_e.sales_tdd_backend.exception.product.ProductAlreadyExistsException;
import com.group_e.sales_tdd_backend.exception.product.ProductNotFoundException;
import com.group_e.sales_tdd_backend.exception.product_group.ProductGroupNotFoundException;
import com.group_e.sales_tdd_backend.mapper.ProductMapper;
import com.group_e.sales_tdd_backend.service.ProductService;
import com.group_e.sales_tdd_backend.use_case.product.GetProductByCodeUseCase;
import com.group_e.sales_tdd_backend.use_case.product.GetProductsUseCase;
import com.group_e.sales_tdd_backend.use_case.product.SaveProductUseCase;
import com.group_e.sales_tdd_backend.use_case.product_group.GetProductGroupByIdUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final GetProductsUseCase getProductsUseCase;
    private final GetProductGroupByIdUseCase getProductGroupByIdUseCase;
    private final GetProductByCodeUseCase getProductByCodeUseCase;
    private final SaveProductUseCase saveProductUseCase;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> getProducts() {
        return productMapper.toResponses(getProductsUseCase.execute());
    }

    @Override
    public ProductResponse registerProduct(ProductRegistrationRequest request) throws ProductGroupNotFoundException,
            ProductAlreadyExistsException {
        ProductGroup productGroup = getProductGroupByIdUseCase.execute(request.getProductGroupId());
        String code = request.getCode();
        try {
            getProductByCodeUseCase.execute(code.toUpperCase());
            throw new ProductAlreadyExistsException(
                    String.format("A Product with the code: %s already exists.", code)
            );
        } catch (ProductNotFoundException ignored) {
        }
        Product newProduct = new Product();
        newProduct.setProductGroupId(productGroup.getId());
        newProduct.setName(request.getName().toUpperCase());
        newProduct.setCode(code.toUpperCase());
        newProduct.setPrice(request.getPrice());
        Product savedProduct = saveProductUseCase.execute(newProduct);
        savedProduct.setProductGroup(productGroup);
        return productMapper.toResponse(savedProduct);
    }
}
