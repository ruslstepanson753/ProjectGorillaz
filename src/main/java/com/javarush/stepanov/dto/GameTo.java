package com.javarush.stepanov.dto;

import com.javarush.stepanov.entity.UserTo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameTo {
    Long id;
    UserTo user;
    int gamesCount;
    int winsCount;
    int lossCount;
    String gameName;
}
