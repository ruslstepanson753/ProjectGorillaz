package com.javarush.stepanov.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder
public class UserTo {
     Long id;
     String login;
     String password;
     Collection<GameTo> games = new ArrayList<>();

     public int getGamesCount() {
          int gamesCount = 0;
          for (GameTo g : games) {
               gamesCount += g.getGamesCount();
          }
          return gamesCount;
     }

     public int getWinsCount() {
          int winsTotalCount = 0;
          for (GameTo g : games) {
               winsTotalCount += g.getWinsCount();
          }
          return winsTotalCount;
     }

     public int getLossCount() {
          int lossTotalCount = 0;
          for (GameTo g : games) {
               lossTotalCount += g.getLossCount();
          }
          return lossTotalCount;
     }
}
