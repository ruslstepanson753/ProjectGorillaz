package com.javarush.stepanov.mapping;

import com.javarush.stepanov.dto.GameTo;
import com.javarush.stepanov.dto.UserTo;
import com.javarush.stepanov.entity.Game;
import com.javarush.stepanov.entity.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-10T12:36:25+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
public class DtoImpl implements Dto {

    @Override
    public GameTo from(Game game) {
        if ( game == null ) {
            return null;
        }

        GameTo.GameToBuilder gameTo = GameTo.builder();

        gameTo.userId( gameUserId( game ) );
        gameTo.login( gameUserLogin( game ) );
        gameTo.id( game.getId() );
        gameTo.gamesCount( game.getGamesCount() );
        gameTo.winsCount( game.getWinsCount() );
        gameTo.lossCount( game.getLossCount() );
        gameTo.gameName( game.getGameName() );

        return gameTo.build();
    }

    @Override
    public UserTo from(User user) {
        if ( user == null ) {
            return null;
        }

        UserTo.UserToBuilder userTo = UserTo.builder();

        userTo.games( mapGames( user.getGames() ) );
        userTo.id( user.getId() );
        userTo.login( user.getLogin() );
        userTo.password( user.getPassword() );

        return userTo.build();
    }

    private Long gameUserId(Game game) {
        if ( game == null ) {
            return null;
        }
        User user = game.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String gameUserLogin(Game game) {
        if ( game == null ) {
            return null;
        }
        User user = game.getUser();
        if ( user == null ) {
            return null;
        }
        String login = user.getLogin();
        if ( login == null ) {
            return null;
        }
        return login;
    }
}
