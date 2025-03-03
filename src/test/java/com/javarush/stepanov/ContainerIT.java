package com.javarush.stepanov;

import com.javarush.stepanov.config.ApplicationProperties;
import com.javarush.stepanov.config.Config;
import com.javarush.stepanov.config.NanoSpring;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import static com.javarush.stepanov.config.ApplicationProperties.*;

public class ContainerIT {

    private final static JdbcDatabaseContainer<?> CONTAINER;

    public static final String DOCKER_IMAGE_NAME = "postgres:16.3";

    static {
        CONTAINER = new PostgreSQLContainer<>(DOCKER_IMAGE_NAME);
        CONTAINER.start();
        ApplicationProperties properties = NanoSpring.find(ApplicationProperties.class);
        properties.setProperty(HIBERNATE_CONNECTION_URL, CONTAINER.getJdbcUrl());
        properties.setProperty(HIBERNATE_CONNECTION_USERNAME, CONTAINER.getUsername());
        properties.setProperty(HIBERNATE_CONNECTION_PASSWORD, CONTAINER.getPassword());
        Config config = NanoSpring.find(Config.class);
        config.fillEmptyRepository();
    }
}
