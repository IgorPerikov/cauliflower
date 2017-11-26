package com.github.igorperikov.cauliflower.common.dto.notes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.annotation.Nonnull;

@Getter
public class ContentWrapperTO {
    @JsonCreator
    public ContentWrapperTO(@Nonnull @JsonProperty("content") String content) {
        this.content = content;
    }

    private String content;
}
