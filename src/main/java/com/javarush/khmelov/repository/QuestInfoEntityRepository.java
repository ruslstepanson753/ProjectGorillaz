package com.javarush.khmelov.repository;

import com.javarush.khmelov.config.SessionCreator;
import com.javarush.khmelov.entity.QuestInfoEntity;
import com.javarush.khmelov.entity.User;

public class QuestInfoEntityRepository extends BaseRepository<QuestInfoEntity> {

    public QuestInfoEntityRepository(SessionCreator sessionCreator) {
        super(sessionCreator, QuestInfoEntity.class);
    }
}
