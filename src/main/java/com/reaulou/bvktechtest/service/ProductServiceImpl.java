package com.reaulou.bvktechtest.service;

import com.reaulou.bvktechtest.core.InternalRequest;
import com.reaulou.bvktechtest.core.InternalResponse;
import com.reaulou.bvktechtest.model.Product;
import com.reaulou.bvktechtest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public InternalResponse addProduct(InternalRequest internalRequest) {
        // retrieve data from request
        Product product = internalRequest.getProduct();

        InternalResponse internalResponse = new InternalResponse();
        try {
            productRepository.save(product);
            // set internalResponse
            internalResponse.setReturnCode("00");
            internalResponse.setReturnDesc("success");
        }catch (Exception e) {
            // set internalResponse
            internalResponse.setReturnCode("99");
            internalResponse.setReturnDesc("internal server error");
        }
        return internalResponse;
    }

    @Override
    public InternalResponse getAllProducts() {
        InternalResponse internalResponse = new InternalResponse();
        try{
            List<Product> productList = productRepository.findAll();
            // set internalResponse
            internalResponse.setProductList(productList);
            internalResponse.setReturnCode("00");
            internalResponse.setReturnDesc("success");
        }catch (Exception e){
            // set internalResponse
            internalResponse.setReturnCode("99");
            internalResponse.setReturnDesc("internal server error");
        }
        return internalResponse;
    }

    @Override
    public InternalResponse getProductById(InternalRequest internalRequest) {
        // retrieve data from request
        Long id = internalRequest.getId();

        InternalResponse internalResponse = new InternalResponse();
        try{
            Optional<Product> product1 = productRepository.findById(id);
            if (product1.isPresent()) {
                Product product = product1.get();
                List<Product> productList = Arrays.asList(product);

                // set internalResponse
                internalResponse.setProductList(productList);
                internalResponse.setReturnCode("00");
                internalResponse.setReturnDesc("success");
            }else{
                // set internalResponse
                internalResponse.setReturnCode("60");
                internalResponse.setReturnDesc("product not found");
            }
        }catch (Exception e){
            // set internalResponse
            internalResponse.setReturnCode("99");
            internalResponse.setReturnDesc("internal server error");
        }
        return internalResponse;
    }

    @Override
    public InternalResponse updateProductById(InternalRequest internalRequest) {
        // retrieve data from request
        Long id = internalRequest.getId();
        Product requestProduct = internalRequest.getProduct();

        InternalResponse internalResponse = new InternalResponse();
        try{
            Optional<Product> product1 = productRepository.findById(id);
            if (product1.isPresent()) {
                Product originalProduct = product1.get();

                if (Objects.nonNull(requestProduct)) {
                    originalProduct.setName(requestProduct.getName());
                    originalProduct.setPrice(requestProduct.getPrice());
                    originalProduct.setQuantity(requestProduct.getQuantity());
                }
                productRepository.save(originalProduct);
                // set internalResponse
                internalResponse.setReturnCode("00");
                internalResponse.setReturnDesc("success");
            }else{
                // set internalResponse
                internalResponse.setReturnCode("60");
                internalResponse.setReturnDesc("product not found");
            }
        }catch (Exception e){
            // set internalResponse
            internalResponse.setReturnCode("99");
            internalResponse.setReturnDesc("internal server error");
        }
        return internalResponse;
    }

    @Override
    public InternalResponse executeProductOrder(InternalRequest internalRequest) {
        // retrieve data from request
        Long id = internalRequest.getId();
        Integer requestQuantity = internalRequest.getQuantity();

        InternalResponse internalResponse = new InternalResponse();

        try{
            Optional<Product> product1 = productRepository.findById(id);

            if(product1.isPresent()){
                Product product = product1.get();
                Integer productQuantity = product.getQuantity();
                if(productQuantity < requestQuantity){
                    // set internalResponse
                    internalResponse.setReturnCode("61");
                    internalResponse.setReturnDesc("insufficient quantity");
                }else if(productQuantity == requestQuantity){
                    productRepository.deleteById(id);
                    // set internalResponse
                    internalResponse.setReturnCode("00");
                    internalResponse.setReturnDesc("success");
                }else{
                    productQuantity -= requestQuantity;
                    product.setQuantity(productQuantity);
                    productRepository.save(product);
                    // set internalResponse
                    internalResponse.setReturnCode("00");
                    internalResponse.setReturnDesc("success");
                }
            }else{
                // set internalResponse
                internalResponse.setReturnCode("60");
                internalResponse.setReturnDesc("product not found");
            }
        }catch (Exception e){
            // set internalResponse
            internalResponse.setReturnCode("99");
            internalResponse.setReturnDesc("internal server error");
        }
        return internalResponse;
    }
}
