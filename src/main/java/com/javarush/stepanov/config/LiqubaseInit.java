package com.javarush.stepanov.config;

import liquibase.Scope;
import liquibase.command.CommandScope;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

@Slf4j
public class LiqubaseInit {
    public void init() {
        log.info(LIQUBASE_INIT_START_INFO);
        try {
            Scope.child(Scope.Attr.resourceAccessor, new ClassLoaderResourceAccessor(), () -> {
                CommandScope update = new CommandScope(LIQUBASE_COMMAND_COPE_UPDATE);
                update.addArgumentValue(LIQUBASE_COMMAND_UPDATE_ARGNAME_CHANGE_LOG, LIQUBASE_URL_CHANGELOG_XML);
                update.addArgumentValue(LIQUBASE_COMMAND_UPDATE_ARGNAME_URL, DB_URL);
                update.addArgumentValue(LIQUBASE_COMMAND_UPDATE_ARGNAME_USERNAME, DB_USERNAME);
                update.addArgumentValue(LIQUBASE_COMMAND_UPDATE_ARGNAME_PASSWORD, DB_PASSWORD);
                update.execute();
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info(LIQUBASE_INIT_END_INFO);
    }
}