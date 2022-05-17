package com.aybuke.tokengateway.request;

import com.google.gson.Gson;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfiguration {

    @Value("${retrofit.timeout}")
    private Long TIMEOUT_IN_SECONDS;

    private OkHttpClient.Builder createDefaultClientBuilder(){
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
    }

    @Bean
    public OkHttpClient secureKeyClient(@Value("${service.security.username}") String username,
                                        @Value("${service.security.password}") String password){
        return createDefaultClientBuilder()
                .addInterceptor(interceptor -> interceptor.proceed(interceptor.request().newBuilder()
                        .header("Authorization", Credentials.basic(username, password))
                        .build()))
                .build();
    }

    @Bean
    public Retrofit.Builder secureKeyBuilder(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder().client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson));
    }

    @Bean
    public IProductServiceRequest productServiceRequest(Retrofit.Builder retrofitBuilder, @Value("${product.service.url}") String baseUrl){
        return retrofitBuilder.baseUrl(baseUrl).build().create(IProductServiceRequest.class);
    }

    @Bean
    public ITransactionServiceRequest transactionServiceRequest(Retrofit.Builder retrofitBuilder, @Value("${transaction.service.url}") String baseUrl){
        return retrofitBuilder.baseUrl(baseUrl).build().create(ITransactionServiceRequest.class);
    }
}
