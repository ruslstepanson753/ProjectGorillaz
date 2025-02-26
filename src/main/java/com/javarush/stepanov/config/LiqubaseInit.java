package com.javarush.stepanov.config;

import com.javarush.stepanov.entity.QuestInfoEntity;
import com.javarush.stepanov.repository.QuestInfoEntityRepository;
import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.List;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

@Slf4j
@AllArgsConstructor
public class LiqubaseInit {
    private final ApplicationProperties properties;

    public static final String CLASSPATH_DB_CHANGELOG_XML = "db/changelog.xml";

    public void start() {
        System.out.println("Running Liquibase...");
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

                QuestInfoEntityRepository questInfoEntityRepository = NanoSpring.find(QuestInfoEntityRepository.class);

                Collection<QuestInfoEntity> questInfoEntitys = questInfoEntityRepository.getAll();
                for (QuestInfoEntity questInfoEntity : questInfoEntitys) {
                    System.out.println(questInfoEntity.toString());
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Running Liquibase...DONE");
    }
}