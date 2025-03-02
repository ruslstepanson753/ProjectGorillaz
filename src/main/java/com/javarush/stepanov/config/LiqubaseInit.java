package com.javarush.stepanov.config;

import com.javarush.stepanov.entity.QuestInfoEntity;
import com.javarush.stepanov.repository.QuestInfoEntityRepository;
import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

@Slf4j
@AllArgsConstructor
public class LiqubaseInit {
    private final ApplicationProperties properties;

    public static final String CLASSPATH_DB_CHANGELOG_XML = "db/changelog.xml";

    private static final String PURPLE = "\u001B[35m";
    private static final String RESET = "\u001B[0m";

    public void start() {
        log.info(PURPLE + LOG_INFO_LIQUBESE_RUN + RESET);
        try {
            Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {
                CommandScope update = new CommandScope("update");
                update.addArgumentValue("changelogFile", "db/changelog.xml");
                String url = properties.getProperty(ApplicationProperties.HIBERNATE_CONNECTION_URL);
                update.addArgumentValue("url", url);
                String username = properties.getProperty(ApplicationProperties.HIBERNATE_CONNECTION_USERNAME);
                update.addArgumentValue("username", username);
                String password = properties.getProperty(ApplicationProperties.HIBERNATE_CONNECTION_PASSWORD);
                update.addArgumentValue("password", password);
                update.execute();

            });
        } catch (Exception e) {
            log.error(PURPLE + "Ошибка Liquibase: " + e.getMessage() + RESET);
            throw new RuntimeException(e);
        }
        log.info(PURPLE + LOG_INFO_LIQUBESE_DONE + RESET);
    }
}
