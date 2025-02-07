package com.javarush.stepanov.repository;

import com.javarush.stepanov.config.SessionCreator;
import com.javarush.stepanov.entity.QuestMap;

public class QuestMapRepository extends BaseRepository<QuestMap> {

    public QuestMapRepository(SessionCreator sessionCreator) {
        super(sessionCreator, QuestMap.class);
    }
}
