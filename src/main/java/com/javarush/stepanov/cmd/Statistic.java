package com.javarush.stepanov.cmd;

import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.entity.Game;
import com.javarush.stepanov.entity.Gamer;
import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.repository.GameRepository;
import com.javarush.stepanov.repository.UserRepository;
import com.javarush.stepanov.service.StatisticService;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class Statistic implements Command {
    @Override
    public String doGet(HttpServletRequest req) {

        List<Game> questGamers = new ArrayList<>();
//        List<Game> questGamers = StatisticService.getGamersList(GAME_QUEST_NAME);
//        List<Game> rouletteGamers = StatisticService.getGamersList(GAME_ROULETTE_NAME);
//        List<Game> quizGamers = StatisticService.getGamersList(GAME_QUIZ_NAME);
        GameRepository gameRepository = NanoSpring.find(GameRepository.class);
        Game game = gameRepository.get(1L);
        questGamers.add(game);
        req.getSession().setAttribute("questGamers", questGamers);
//        req.getSession().setAttribute("questGamers", questGamers);
//        req.getSession().setAttribute("rouletteGamers", rouletteGamers);
//        req.getSession().setAttribute("quizGamers", quizGamers);
        return getView();
    }
}
