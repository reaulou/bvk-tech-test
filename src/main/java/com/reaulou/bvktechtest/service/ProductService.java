package com.reaulou.bvktechtest.service;

import java.util.List;

import com.reaulou.bvktechtest.core.InternalRequest;
import com.reaulou.bvktechtest.core.InternalResponse;
import com.reaulou.bvktechtest.model.Product;

public interface ProductService {
    InternalResponse addProduct(InternalRequest internalRequest);

    List<Product> getAllProducts();

    Integer getProductQuantityById(Long id);

    Product updateProductQuantityById(Long id, Integer quantity);

    Integer executeProductOrder(Long id, Integer orderedQuantity);
}
