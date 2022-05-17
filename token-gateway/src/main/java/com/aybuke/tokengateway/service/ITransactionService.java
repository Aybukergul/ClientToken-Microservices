package com.aybuke.tokengateway.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.List;

public interface ITransactionService {
    void deleteTransaction(Long transactionId);

    JsonElement saveTransaction(JsonObject jsonElement);

    List<JsonElement> getAllTransactions(Long userId);
}
