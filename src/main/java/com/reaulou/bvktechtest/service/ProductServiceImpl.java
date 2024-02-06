package com.reaulou.bvktechtest.service;

import com.reaulou.bvktechtest.model.Product;
import com.reaulou.bvktechtest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> fetchAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }

    @Override
    public Integer getProductQuantityById(Long id) {
        Optional<Product> product1 = productRepository.findById(id);
        if (product1.isPresent()) {
            Product product = product1.get();
            return product.getQuantity();
        }
        return null;
    }

    @Override
    public Product updateProductQuantityById(Long id, Integer quantity) {
        Optional<Product> product1 = productRepository.findById(id);

        if (product1.isPresent()) {
            Product originalProduct = product1.get();

            if (Objects.nonNull(quantity) && quantity != 0) {
                originalProduct.setQuantity(quantity);
            }
            return productRepository.save(originalProduct);
        }
        return null;
    }

    @Override
    public Integer executeProductOrder(Long id, Integer orderedQuantity) {
        Optional<Product> product1 = productRepository.findById(id);

        if(product1.isPresent()){
            Product product = product1.get();
            Integer productQuantity = product.getQuantity();
            if(productQuantity < orderedQuantity){
                return null;
            }else if(productQuantity == orderedQuantity){
                productRepository.deleteById(id);
                return 0;
            }else{
                productQuantity -= orderedQuantity;
                product.setQuantity(productQuantity);
                productRepository.save(product);
                return productQuantity;
            }
        }

        return null;
    }
}
