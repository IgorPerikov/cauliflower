package com.github.igorperikov.cauliflower.auth.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("auth_info")
@AllArgsConstructor
@Getter
public class AuthEntity {
    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, name = "login")
    private final String login;

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, name = "hashed_password")
    private final String hashedPassword;

    @Column(value = "user_id")
    private final UUID userId;
}
