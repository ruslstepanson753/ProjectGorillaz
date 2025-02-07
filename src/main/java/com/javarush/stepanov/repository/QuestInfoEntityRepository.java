package com.javarush.stepanov.repository;

import com.javarush.stepanov.config.SessionCreator;
import com.javarush.stepanov.entity.QuestInfoEntity;

public class QuestInfoEntityRepository extends BaseRepository<QuestInfoEntity> {

    public QuestInfoEntityRepository(SessionCreator sessionCreator) {
        super(sessionCreator, QuestInfoEntity.class);
    }
}
