package com.javarush.stepanov.cmd;

import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.Game;
import com.javarush.stepanov.repository.GameRepository;
import com.javarush.stepanov.service.RouletteService;
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
        req.getSession().setAttribute("questGamers", questGamers);
        req.getSession().setAttribute("rouletteGamers", rouletteGamers);
        req.getSession().setAttribute("quizGamers", quizGamers);
        return getView();
    }
}
