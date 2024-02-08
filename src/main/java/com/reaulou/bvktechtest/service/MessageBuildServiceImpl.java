package com.reaulou.bvktechtest.service;

import com.reaulou.bvktechtest.core.InternalRequest;
import com.reaulou.bvktechtest.core.InternalResponse;
import com.reaulou.bvktechtest.model.Product;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MessageBuildServiceImpl implements MessageBuildService{
    @Override
    public InternalRequest parseExternalRequest(String body){
        JSONObject bodyJSON = new JSONObject(body);
        JSONObject messageJSON = bodyJSON.getJSONObject("message");

        Long id = messageJSON.optLong("id");
        String name = messageJSON.optString("name");
        Integer price = messageJSON.optInt("price");
        Integer quantity = messageJSON.optInt("quantity");

        InternalRequest internalRequest = new InternalRequest();
        internalRequest.setId(id);
        internalRequest.setName(name);
        internalRequest.setPrice(price);
        internalRequest.setQuantity(quantity);
        internalRequest.setProduct(new Product(name, price, quantity));

        return internalRequest;
    }


    @Override
    public String buildResponseBody(InternalResponse internalResponse){
        return buildResponseBody(null, internalResponse);
    }

    @Override
    public String buildResponseBody(Object payload, InternalResponse internalResponse) {
        String returnCode = internalResponse.getReturnCode();

        JSONObject bodyJSON = new JSONObject();
        JSONObject returnCodeJSON = new JSONObject();

        // return code mappings:
        switch (returnCode) {
            case "00":
                bodyJSON.put("message", "Operation success");
                if(payload != null){
                    bodyJSON.put("payload", payload);
                }
                returnCodeJSON.put("code", returnCode);
                returnCodeJSON.put("desc", "success");
                break;
            case "99":
                returnCodeJSON.put("code", returnCode);
                returnCodeJSON.put("desc", "Internal Server Error");
                break;
            case "70":
                returnCodeJSON.put("code", returnCode);
                returnCodeJSON.put("desc", "Bad Request");
                break;
            case "60":
                bodyJSON.put("message", "Product not exist");
                returnCodeJSON.put("code", returnCode);
                returnCodeJSON.put("desc", "success");
                break;
            case "61":
                bodyJSON.put("message", "Insufficient quantity");
                returnCodeJSON.put("code", returnCode);
                returnCodeJSON.put("desc", "success");
                break;
        }
        bodyJSON.put("returnCode", returnCodeJSON);

        return bodyJSON.toString();
    }
}
