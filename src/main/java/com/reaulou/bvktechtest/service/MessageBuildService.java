package com.reaulou.bvktechtest.service;

import com.reaulou.bvktechtest.core.InternalRequest;
import com.reaulou.bvktechtest.model.Product;
import org.json.JSONObject;
import org.springframework.http.RequestEntity;

import java.util.List;

public interface MessageBuildService {
    InternalRequest parseExternalRequest(String body);
    Product parseProduct(String body);

    String buildResponseBody(String key, Object messageContent);

    String buildResponseBody(Object messageContent);

    JSONObject parseRequestMessage(String body);
}
