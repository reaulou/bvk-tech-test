package com.reaulou.bvktechtest.service;

import com.reaulou.bvktechtest.core.InternalRequest;
import com.reaulou.bvktechtest.core.InternalResponse;
import com.reaulou.bvktechtest.model.Product;
import org.json.JSONObject;
import org.springframework.http.RequestEntity;

import java.util.List;

public interface MessageBuildService {
    InternalRequest parseExternalRequest(String body);
    String buildResponseBody(InternalResponse internalResponse);
    String buildResponseBody(Object payload, InternalResponse internalResponse);

    JSONObject parseRequestMessage(String body);
}
