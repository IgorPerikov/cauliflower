package com.github.igorperikov.cauliflower.common.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.annotation.Nonnull;

@Getter
public class Credentials {
    @Nonnull
    private String login;

    @Nonnull
    private String password;

    @JsonCreator
    public Credentials(
            @Nonnull @JsonProperty(value = "login") String login,
            @Nonnull @JsonProperty(value = "password") String password
    ) {
        this.login = login;
        this.password = password;
    }
}
