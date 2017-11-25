package com.github.igorperikov.cauliflower.auth.service;

import com.github.igorperikov.cauliflower.auth.exception.IncorrectCredentialsException;
import com.github.igorperikov.cauliflower.auth.exception.LoginAlreadyTakenException;
import com.github.igorperikov.cauliflower.auth.repository.AuthEntity;
import com.github.igorperikov.cauliflower.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final HashingUtility hashingUtility;

    @Autowired
    public AuthService(AuthRepository authRepository, HashingUtility hashingUtility) {
        this.authRepository = authRepository;
        this.hashingUtility = hashingUtility;
    }

    public Mono<UUID> createNewUser(String login, String password) {
        return isLoginFree(login).flatMap(isFree -> {
            if (isFree) {
                UUID userId = UUID.randomUUID();
                AuthEntity newUser = new AuthEntity(login, hashingUtility.hash(password), userId);
                return authRepository.createNewUser(newUser);
            } else {
                throw new LoginAlreadyTakenException();
            }
        });
    }

    private Mono<Boolean> isLoginFree(String login) {
        return authRepository.findByLogin(login).map(count -> count == 0);
    }

    public Mono<UUID> authorize(String login, String password) {
        return authRepository.findByLoginAndPassword(login, hashingUtility.hash(password))
                .switchIfEmpty(Mono.error(new IncorrectCredentialsException()))
                .map(AuthEntity::getUserId);
    }
}
