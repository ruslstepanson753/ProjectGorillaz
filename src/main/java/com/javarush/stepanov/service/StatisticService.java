package com.javarush.stepanov.service;

import com.javarush.stepanov.dto.GameTo;
import com.javarush.stepanov.entity.Game;
import com.javarush.stepanov.mapping.Dto;
import com.javarush.stepanov.repository.GameRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
@AllArgsConstructor
public class StatisticService {
    private final GameRepository gameRepository;
    private final Dto dto;

    public Set<GameTo> getGamers(String gameName) {
        Collection<Game> collection = gameRepository.getAll();
        Set<GameTo> games = collection.stream()
                .filter(game -> gameName.equals(game.getGameName()))
                .sorted(Comparator
                        .comparingInt(Game::getWinsCount).reversed()
                        .thenComparingInt(Game::getGamesCount)
                )
                .map(dto::from)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return games;
    }
}
