package com.github.igorperikov.cauliflower.auth.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class AuthRepository {
    private final ReactiveCassandraTemplate cassandraTemplate;

    @Autowired
    public AuthRepository(ReactiveCassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    public Mono<AuthEntity> findByLoginAndPassword(String login, String hashedPassword) {
        System.out.println("repo called");
        return cassandraTemplate.selectOne(
                Query.query(
                        Criteria.where("login").is(login),
                        Criteria.where("hashed_password").is(hashedPassword)
                ),
                AuthEntity.class);
    }

    public Mono<UUID> createNewUser(AuthEntity authEntity) {
        return cassandraTemplate.insert(authEntity).then(Mono.just(authEntity.getUserId()));
    }

    public Mono<Long> findByLogin(String login) {
        return cassandraTemplate.select(Query.query(Criteria.where("login").is(login)), AuthEntity.class).count();
    }
}
