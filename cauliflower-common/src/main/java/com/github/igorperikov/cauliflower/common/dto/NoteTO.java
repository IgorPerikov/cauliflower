package com.github.igorperikov.cauliflower.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class NoteTO {
    private final UUID userId;
    private final UUID noteId;
    private final String content;
}
