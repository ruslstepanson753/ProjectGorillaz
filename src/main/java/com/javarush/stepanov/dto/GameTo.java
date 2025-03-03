package com.javarush.stepanov.dto;

import com.javarush.stepanov.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameTo {
    Long id;
    User user;
    int gamesCount;
    int winsCount;
    int lossCount;
    String gameName;
}
