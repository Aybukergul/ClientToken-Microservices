package com.aybuke.tokengateway.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

public interface IProductService {
    void deleteProduct(Long productId);

    JsonElement saveProduct(JsonObject jsonElement);

    List<JsonElement> getAllProducts();
}
