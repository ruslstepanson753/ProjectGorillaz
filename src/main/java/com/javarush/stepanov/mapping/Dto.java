package com.javarush.stepanov.mapping;

import com.javarush.stepanov.dto.*;
import com.javarush.stepanov.entity.*;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collection;

@Mapper
public interface Dto {
    Dto MAPPER = Mappers.getMapper(Dto.class);

    @Mappings({
            @Mapping(target = "userId", source = "user.id")
    })
    GameTo from(Game game);

    @Named("mapGames")
    default Collection<GameTo> mapGames(Collection<Game> games) {
        if (games == null) return new ArrayList<>();
        return games.stream().map(this::from).toList();
    }

    @Mapping(target = "games", qualifiedByName = "mapGames")
    UserTo from(User user);
}
