package com.javarush.stepanov.service;

import com.javarush.stepanov.repository.GameRepository;
import com.javarush.stepanov.repository.QuestInfoEntityRepository;
import com.javarush.stepanov.repository.QuestMapRepository;

public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}