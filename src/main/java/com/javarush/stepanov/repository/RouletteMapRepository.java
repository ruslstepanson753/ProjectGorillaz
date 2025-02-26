package com.javarush.stepanov.repository;

import com.javarush.stepanov.config.SessionCreator;
import com.javarush.stepanov.entity.RouletteMap;
import jakarta.transaction.Transactional;
@Transactional
public class RouletteMapRepository extends BaseRepository<RouletteMap> {

    public RouletteMapRepository(SessionCreator sessionCreator) {
        super(sessionCreator, RouletteMap.class);
    }
}
