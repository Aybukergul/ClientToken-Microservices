package com.aybuke.tokengateway.util;

import com.google.gson.JsonElement;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
public class RetrofitUtils {
    public static <T> T executeInBlock(Call<T> request){
        try {
            Response<T> response = request.execute();
            if(!response.isSuccessful())
                log.error("SaveProduct Request failed with status code: {} and reason: {} ", response.code(), response.body().toString());

            return response.body() == null ? null : response.body();
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }

    }
}
