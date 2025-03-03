package com.javarush.stepanov.mapping;

import com.javarush.stepanov.dto.*;
import com.javarush.stepanov.entity.*;
import com.javarush.stepanov.entity.UserTo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Dto {

    Dto MAPPER = Mappers.getMapper(Dto.class);

    GameTo from(Game game);

    QuestInfoEntityTo from(QuestInfoEntity questInfoEntity);

    QuestMapTo from(QuestMap questMap);

    RouletteMapTo from(RouletteMap rouletteMap);

    UserTo from(com.javarush.stepanov.dto.UserTo userTo);
    com.javarush.stepanov.dto.UserTo from(UserTo user);

}
