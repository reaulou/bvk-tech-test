package com.reaulou.bvktechtest.service;

import com.reaulou.bvktechtest.model.Product;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public String buildResponseBody(String key, Object messageContent) {
        JSONObject messageJSON = new JSONObject();
        messageJSON.put(key, messageContent);

        JSONObject bodyJSON = new JSONObject();
        bodyJSON.put("message", messageJSON);

        return bodyJSON.toString();
    }

    @Override
    public String buildResponseBody(Object messageContent) {
        JSONObject bodyJSON = new JSONObject();
        bodyJSON.put("message", messageContent);

        return bodyJSON.toString();
    }

    @Override
    public JSONObject parseRequestMessage(String body) {
        JSONObject bodyJSON = new JSONObject(body);
        JSONObject messageJSON = bodyJSON.getJSONObject("message");

        return messageJSON;
    }
}
