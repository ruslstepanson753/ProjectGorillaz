package com.javarush.stepanov.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameTo {
    Long id;
    Long userId;
    int gamesCount;
    int winsCount;
    int lossCount;
    String gameName;
}
