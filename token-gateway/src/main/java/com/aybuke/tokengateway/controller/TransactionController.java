package com.aybuke.tokengateway.controller;

import com.aybuke.tokengateway.security.UserPrincipal;
import com.aybuke.tokengateway.service.ITransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("gateway/transaction")
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> saveTransaction(@RequestBody Map<String, Object> jsonElement) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonObject transactionJson = JsonParser.parseString(objectMapper.writeValueAsString(jsonElement)).getAsJsonObject();
        return ResponseEntity.ok(transactionService.saveTransaction(transactionJson));
    }

    @DeleteMapping("{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId){
        transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllTransactions(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return ResponseEntity.ok(transactionService.getAllTransactions(userPrincipal.getId()));
    }

}
