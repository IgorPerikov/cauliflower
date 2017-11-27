package com.github.igorperikov.cauliflower.notes.repository;

import com.github.igorperikov.cauliflower.notes.entity.NoteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@Repository
public class NotesRepository {
    private final ReactiveCassandraTemplate cassandraTemplate;

    @Autowired
    public NotesRepository(ReactiveCassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    public Flux<NoteEntity> getAll(UUID userId) {
        return cassandraTemplate.select(
                Query.query(Criteria.where("user_id").is(userId)),
                NoteEntity.class
        );
    }

    public Mono<NoteEntity> createNote(NoteEntity note) {
        return cassandraTemplate.insert(note).map(Function.identity());
    }

    public Mono<Boolean> deleteNote(UUID userId, UUID noteId) {
        return cassandraTemplate.delete(
                Query.query(
                        Criteria.where("user_id").is(userId),
                        Criteria.where("note_id").is(noteId)
                ), NoteEntity.class
        );
    }
}
