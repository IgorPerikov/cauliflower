package com.github.igorperikov.cauliflower.notes.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("user_notes")
@AllArgsConstructor
@Getter
public class NoteEntity {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, name = "user_id")
    private final UUID userId;
    /**
     * timeuuid
     */
    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, name = "note_id")
    private final UUID noteId;
    private final String content;
}
