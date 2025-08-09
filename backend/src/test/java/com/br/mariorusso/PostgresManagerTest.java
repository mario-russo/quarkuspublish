package com.br.mariorusso;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresManagerTest implements QuarkusTestResourceLifecycleManager {
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("test")
            .withUsername("user")
            .withPassword("password");
    @Override
    public Map<String, String> start() {
        POSTGRES.start();

        Map<String, String> config = new HashMap<>();
        config.put("quarkus.datasource.jdbc.url", POSTGRES.getJdbcUrl());
        config.put("quarkus.datasource.username", POSTGRES.getUsername());
        config.put("quarkus.datasource.password", POSTGRES.getPassword());
        config.put("quarkus.datasource.db-kind", "postgresql");
        config.put("quarkus.hibernate-orm.database.generation", "drop-and-create");
        config.put("quarkus.hibernate-orm.sql-load-script","import.sql");
        return config;
    }

    @Override
    public void stop() {
        if(POSTGRES != null){
            POSTGRES.stop();
        }

    }
}
