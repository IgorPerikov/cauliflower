package com.github.igorperikov.cauliflower.common.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UUIDWrapper {
    @JsonProperty
    private UUID userId;

    @JsonCreator
    public UUIDWrapper(@JsonProperty(value = "userId") UUID userId) {
        this.userId = userId;
    }
}
