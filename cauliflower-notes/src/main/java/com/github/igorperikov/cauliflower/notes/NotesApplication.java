package com.github.igorperikov.cauliflower.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@SpringBootApplication
@EnableReactiveCassandraRepositories(basePackages = "com.github.igorperikov.cauliflower.notes.repository")
public class NotesApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
    }
}
