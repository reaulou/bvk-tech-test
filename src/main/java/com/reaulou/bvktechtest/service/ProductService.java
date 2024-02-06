package com.reaulou.bvktechtest.service;

import java.util.List;

import com.reaulou.bvktechtest.model.Product;

public interface ProductService {
    Product addProduct(Product product);

    List<Product> fetchAllProducts();

    Product getProductById(Long id);

    Product updateProductQuantityById(Long id, Integer quantity);

    Integer executeProductOrder(Long id, Integer orderedQuantity);
}
