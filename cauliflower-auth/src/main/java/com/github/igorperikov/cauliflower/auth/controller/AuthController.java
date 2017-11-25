package com.github.igorperikov.cauliflower.auth.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.igorperikov.cauliflower.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authorize")
    public Mono<UUIDWrapper> authorize(@RequestBody @Valid Credentials credentials) {
        System.out.println("controller called");
        return authService.authorize(credentials.login, credentials.password).map(UUIDWrapper::new);
    }

    @PostMapping("/register")
    public Mono<UUIDWrapper> register(@RequestBody @Valid Credentials credentials) {
        return authService.createNewUser(credentials.login, credentials.password).map(UUIDWrapper::new);
    }

    private static class UUIDWrapper {
        @JsonProperty
        UUID userId;

        UUIDWrapper(UUID userId) {
            this.userId = userId;
        }
    }

    private static class Credentials {
        @NotNull
        @JsonProperty
        String login;

        @NotNull
        @JsonProperty
        String password;
    }
}
