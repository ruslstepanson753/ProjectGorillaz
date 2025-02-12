package com.javarush.stepanov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Gamer {
    private int place;
    private String name;
    private int gamesPlayed;
    private int wins;
}
