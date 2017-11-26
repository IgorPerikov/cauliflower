package com.github.igorperikov.cauliflower.auth.controller;

import com.github.igorperikov.cauliflower.auth.service.AuthService;
import com.github.igorperikov.cauliflower.common.dto.auth.Credentials;
import com.github.igorperikov.cauliflower.common.dto.auth.UUIDWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authorize")
    public Mono<UUIDWrapper> authorize(@RequestBody @Valid Credentials credentials) {
        return authService.authorize(credentials.getLogin(), credentials.getPassword()).map(UUIDWrapper::new);
    }

    @PostMapping("/register")
    public Mono<UUIDWrapper> register(@RequestBody @Valid Credentials credentials) {
        return authService.createNewUser(credentials.getLogin(), credentials.getPassword()).map(UUIDWrapper::new);
    }
}
