package com.javarush.khmelov.repository;

import com.javarush.khmelov.config.SessionCreator;
import com.javarush.khmelov.entity.QuestInfoEntity;
import com.javarush.khmelov.entity.QuestMap;

public class QuestMapRepository extends BaseRepository<QuestMap> {

    public QuestMapRepository(SessionCreator sessionCreator) {
        super(sessionCreator, QuestMap.class);
    }
}
