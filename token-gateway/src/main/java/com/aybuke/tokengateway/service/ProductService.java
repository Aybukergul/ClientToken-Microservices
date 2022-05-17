package com.aybuke.tokengateway.service;

import com.aybuke.tokengateway.request.IProductServiceRequest;
import com.aybuke.tokengateway.util.RetrofitUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductServiceRequest productServiceRequest;


    @Override
    public void deleteProduct(Long productId){
        RetrofitUtils.executeInBlock(productServiceRequest.deleteProduct(productId));
    }

    @Override
    public JsonElement saveProduct(JsonObject jsonElement) {
        return RetrofitUtils.executeInBlock(productServiceRequest.saveProduct(jsonElement));
    }

    @Override
    public List<JsonElement> getAllProducts(){
        return RetrofitUtils.executeInBlock(productServiceRequest.getAllProducts());
    }
}
