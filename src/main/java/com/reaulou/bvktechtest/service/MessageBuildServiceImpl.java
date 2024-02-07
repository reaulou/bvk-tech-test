package com.reaulou.bvktechtest.service;

import com.reaulou.bvktechtest.model.Product;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MessageBuildServiceImpl implements MessageBuildService{

    @Override
    public Product parseProduct(String body) {
        JSONObject bodyJSON = new JSONObject(body);
        JSONObject messageJSON = bodyJSON.getJSONObject("message");

        String name = messageJSON.getString("name");
        Integer price = messageJSON.getInt("price");
        Integer quantity = messageJSON.getInt("quantity");

        return new Product(name, price, quantity);
    }

    @Override
    public String buildResponseBody(Product responseProduct) {
        String name = responseProduct.getName();
        Integer price = responseProduct.getPrice();
        Integer quantity = responseProduct.getQuantity();

        JSONObject messageJSON = new JSONObject();
        messageJSON.put("quantity", quantity);
        messageJSON.put("price", price);
        messageJSON.put("name", name);

        JSONObject bodyJSON = new JSONObject();
        bodyJSON.put("message", messageJSON);

        return bodyJSON.toString();
    }
}
