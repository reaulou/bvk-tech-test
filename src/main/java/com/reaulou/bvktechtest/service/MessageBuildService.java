package com.reaulou.bvktechtest.service;

import com.reaulou.bvktechtest.model.Product;

public interface MessageBuildService {
    Product parseProduct(String body);

    String buildResponseBody(Product responseProduct);
}
