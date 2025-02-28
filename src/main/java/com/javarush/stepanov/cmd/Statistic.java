package com.javarush.stepanov.cmd;

import com.javarush.stepanov.entity.Game;
import com.javarush.stepanov.service.StatisticService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.Set;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class Statistic implements Command {
    private final StatisticService statisticService;

    public Statistic(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        Map<String, Set<Game>> gameStats = Map.of(
                STATISTIC_ATTRIBUTE_QUEST_GAMERS, statisticService.getGamers(GAME_QUEST_NAME),
                STATISTIC_ATTRIBUTE_ROULETTE_GAMERS, statisticService.getGamers(GAME_ROULETTE_NAME),
                STATISTIC_ATTRIBUTE_QUIZ_GAMERS, statisticService.getGamers(GAME_QUIZ_NAME)
        );

        gameStats.forEach(req.getSession()::setAttribute);

        return getView();
    }
}
