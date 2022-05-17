package com.aybuke.tokengateway.service;

import com.aybuke.tokengateway.request.ITransactionServiceRequest;
import com.aybuke.tokengateway.util.RetrofitUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements ITransactionService{
    @Autowired
    private ITransactionServiceRequest transactionServiceRequest;

    @Override
    public void deleteTransaction(Long transactionId) {
        RetrofitUtils.executeInBlock(transactionServiceRequest.deleteTransaction(transactionId));
    }

    @Override
    public JsonElement saveTransaction(JsonObject jsonElement) {
        return RetrofitUtils.executeInBlock(transactionServiceRequest.saveTransaction(jsonElement));
    }

    @Override
    public List<JsonElement> getAllTransactions(Long userId) {
        return RetrofitUtils.executeInBlock(transactionServiceRequest.getAllTransactions(userId));
    }


}
