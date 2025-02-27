package com.javarush.stepanov.entity;

import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.repository.GameRepository;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Collection<Game> games = new ArrayList<>();

    public int getGamesCount() {
        int gamesCount = 0;
        for (Game g : games) {
            gamesCount += g.getGamesCount();
        }
        return gamesCount;
    }

    public int getWinsCount() {
        int winsTotalCount = 0;
        for (Game g : games) {
            winsTotalCount += g.getWinsCount();
        }
        return winsTotalCount;
    }

    public int getLossCount() {
        int lossTotalCount = 0;
        for (Game g : games) {
            lossTotalCount += g.getLossCount();
        }
        return lossTotalCount;
    }

    public void setWinsCount(String nameGame) {
        Game game = getGame(nameGame);
        game.setGamesCount(game.getGamesCount() + 1);
        game.setWinsCount(game.getWinsCount() + 1);
    }

    public void setLossCount(String nameGame) {
        Game game = getGame(nameGame);
        game.setGamesCount(game.getGamesCount() + 1);
        game.setLossCount(game.getLossCount() + 1);
    }

    private Game getGame(String nameGame) {
        for (Game g : games) {
            if (g.getGameName().equals(nameGame)) {
                return g;
            }
        }
        return addGame(nameGame);
    }

    public Game addGame(String nameGame) {
        GameRepository gameRepository = NanoSpring.find(GameRepository.class);
        Game game = new Game();
        game.setGameName(nameGame);
        game.setUser(this);
        gameRepository.create(game);
        games.add(game);
        return game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return 42;
    }
}


