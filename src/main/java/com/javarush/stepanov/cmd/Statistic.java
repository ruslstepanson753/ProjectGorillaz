package com.javarush.stepanov.cmd;

import com.javarush.stepanov.entity.Game;
import com.javarush.stepanov.service.StatisticService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Set;
import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class Statistic implements Command {
    private final StatisticService statisticService;

    public Statistic(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public String doGet(HttpServletRequest req) {
        Set<Game> questGamers = statisticService.getGamers(GAME_QUEST_NAME);
        Set<Game> rouletteGamers = statisticService.getGamers(GAME_ROULETTE_NAME);
        Set<Game> quizGamers = statisticService.getGamers(GAME_QUIZ_NAME);

        req.getSession().setAttribute(STATISTIC_ATTRIBUTE_QUEST_GAMERS, questGamers);
        req.getSession().setAttribute(STATISTIC_ATTRIBUTE_ROULETTE_GAMERS, rouletteGamers);
        req.getSession().setAttribute(STATISTIC_ATTRIBUTE_QUIZ_GAMERS, quizGamers);

        return getView();
    }
}
