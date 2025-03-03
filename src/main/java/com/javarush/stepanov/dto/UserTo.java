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
          if(games == null) return 0;
          int gamesCount = 0;
          for (GameTo g : games) {
               gamesCount += g.getGamesCount();
          }
          return gamesCount;
     }

     public int getWinsCount() {
          if(games == null) return 0;
          int winsTotalCount = 0;
          for (GameTo g : games) {
               winsTotalCount += g.getWinsCount();
          }
          return winsTotalCount;
     }

     public int getLossCount() {
          if(games == null) return 0;
          int lossTotalCount = 0;
          for (GameTo g : games) {
               lossTotalCount += g.getLossCount();
          }
          return lossTotalCount;
     }
}
