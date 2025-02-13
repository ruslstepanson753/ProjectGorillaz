package com.javarush.stepanov.service;

import com.javarush.stepanov.entity.Game;
import com.javarush.stepanov.repository.GameRepository;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticService {
    GameRepository gameRepository;
    public StatisticService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Set<Game> getGamers(String gameName) {
        Collection<Game> collection = gameRepository.getAll();
        Set<Game> games = collection.stream()
                .filter(game -> gameName.equals(game.getGameName()))
                .sorted(Comparator
                        .comparingInt(Game::getWinsCount).reversed()
                        .thenComparingInt(Game::getGamesCount)
                )
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return games;
    }
}
