package com.aybuke.tokengateway.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ITransactionServiceRequest {
    @POST("/api/transaction")
    Call<JsonObject> saveTransaction(@Body JsonObject requestBody);

    @DELETE("/api/transaction/{transactionId}")
    Call<Void> deleteTransaction(@Path("transactionId") Long transactionId);

    @GET("/api/transaction/{userId}")
    Call<List<JsonElement>> getAllTransactions(@Path("userId") Long userId);
}
