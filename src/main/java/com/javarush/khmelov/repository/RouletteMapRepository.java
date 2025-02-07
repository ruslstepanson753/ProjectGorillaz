package com.javarush.khmelov.repository;

import com.javarush.khmelov.config.SessionCreator;
import com.javarush.khmelov.entity.QuestMap;
import com.javarush.khmelov.entity.RouletteMap;

public class RouletteMapRepository extends BaseRepository<RouletteMap> {

    public RouletteMapRepository(SessionCreator sessionCreator) {
        super(sessionCreator, RouletteMap.class);
    }
}
