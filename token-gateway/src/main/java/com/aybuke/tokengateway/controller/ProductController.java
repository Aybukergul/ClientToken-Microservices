package com.aybuke.tokengateway.controller;

import com.aybuke.tokengateway.service.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("gateway/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Map<String, Object> product) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonObject productJson = JsonParser.parseString(objectMapper.writeValueAsString(product)).getAsJsonObject();
        return ResponseEntity.ok(productService.saveProduct(productJson));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> listProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

}