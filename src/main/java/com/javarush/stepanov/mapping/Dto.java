package com.javarush.stepanov.mapping;

import com.javarush.stepanov.dto.*;
import com.javarush.stepanov.entity.*;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

@Mapper
public interface Dto {

    Dto MAPPER = Mappers.getMapper(Dto.class);

    GameTo from(Game game);

    QuestInfoEntityTo from(QuestInfoEntity questInfoEntity);

    QuestMapTo from(QuestMap questMap);

    RouletteMapTo from(RouletteMap rouletteMap);

    User from(UserTo userTo);

    UserTo from(User user);

}
