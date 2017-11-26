package com.github.igorperikov.cauliflower.front.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.igorperikov.cauliflower.common.dto.auth.Credentials;
import com.github.igorperikov.cauliflower.common.dto.auth.UUIDWrapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.UUID;

@Service
public class AuthService {
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Value("${auth.application.address}")
    private String authUrl;

    @Value("${auth.application.port}")
    private int authPort;

    @Autowired
    public AuthService(OkHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    @Nullable
    public UUID authorize(String login, String unhashedPassword) throws Exception {
        String content = objectMapper.writeValueAsString(new Credentials(login, unhashedPassword));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), content);
        Request request = new Request.Builder()
                .url("http://" + authUrl + ":" + authPort + "/authorize")
                .post(requestBody)
                .build();
        Response response = httpClient.newCall(request).execute();
        if (response.code() == 200) {
            return objectMapper.readValue(response.body().string(), UUIDWrapper.class).getUserId();
        } else {
            return null;
        }
    }

    @Nullable
    public UUID registerNewUser(String login, String unhashedPassword) throws Exception {
        String content = objectMapper.writeValueAsString(new Credentials(login, unhashedPassword));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), content);
        Request request = new Request.Builder()
                .url("http://" + authUrl + ":" + authPort + "/register")
                .post(requestBody)
                .build();
        Response response = httpClient.newCall(request).execute();
        if (response.code() == 200) {
            return objectMapper.readValue(response.body().string(), UUIDWrapper.class).getUserId();
        } else {
            return null;
        }
    }
}
