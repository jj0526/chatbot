package com.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainersConfiguration {
    @Value("${DATABSE_USERNAME}")
    private String username;
    @Value("${DATABASE_PASSWORD}")
    private String password;
    @Value("${DATABASE_NAME}")
    private String databaseName;

    @Bean
    @ServiceConnection
    PostgreSQLContainer postgresContainer() {
        return new PostgreSQLContainer(DockerImageName
                .parse("groonga/pgroonga:latest-alpine-18-slim")
                .asCompatibleSubstituteFor("postgres"))
                .withDatabaseName(databaseName)
                .withUsername(username)
                .withPassword(password);


    }
}
