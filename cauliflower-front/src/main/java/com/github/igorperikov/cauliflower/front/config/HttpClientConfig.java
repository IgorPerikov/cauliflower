package com.github.igorperikov.cauliflower.front.config;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class HttpClientConfig {
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(10, 30, TimeUnit.SECONDS))
                .build();
    }
}
