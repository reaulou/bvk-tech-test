package com.reaulou.bvktechtest.service;

import java.util.List;

import com.reaulou.bvktechtest.core.InternalRequest;
import com.reaulou.bvktechtest.core.InternalResponse;
import com.reaulou.bvktechtest.model.Product;

public interface ProductService {
    InternalResponse addProduct(InternalRequest internalRequest);

    InternalResponse getAllProducts();

    InternalResponse getProductById(InternalRequest internalRequest);

    InternalResponse updateProductById(InternalRequest internalRequest);

    InternalResponse executeProductOrder(InternalRequest internalRequest);
}
