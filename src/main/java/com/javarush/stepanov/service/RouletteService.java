package com.javarush.stepanov.service;

import com.javarush.stepanov.entity.RouletteMap;
import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.repository.RouletteMapRepository;
import com.javarush.stepanov.util.UrlHelper;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class RouletteService extends GameService {
    private final RouletteMapRepository rouletteMapRepository;
    private final Random random = new Random();
    private final Map<String, String> rouletteMap;

    public RouletteService(RouletteMapRepository rouletteMapRepository, UserService userService) {
        super(userService);
        this.rouletteMapRepository = rouletteMapRepository;
        this.rouletteMap = getRoulletteMap();
    }

    public Map<String, String> getRoulletteMap() {
        Map<String, String> map = new HashMap<String, String>();
        List<RouletteMap> rouletteMapList = (List<RouletteMap>) rouletteMapRepository.getAll();
        rouletteMapList.forEach(questMap -> {
            map.put(questMap.getKey(), questMap.getValue());
        });
        return map;
    }

    @Override
    public Map<String, Object> processAttributes(String userAnswer, User user) {
        Map<String, Object> attributesToView = new HashMap<>();
        if (userAnswer == null) {
            fillViewAttributes(attributesToView);
        } else {
            fillFinishViewAttributes(attributesToView, userAnswer, user);
        }

        return attributesToView;
    }

    @Override
    void fillViewAttributes(Map<String, Object> attributesToView) {
        List.of(ROLETTESERVICE_MAP_START_DESCRIPTION,
                        ROLETTESERVICE_MAP_RED_BUTTON_DESCRIPTION,
                        ROLETTESERVICE_MAP_BLACK_BUTTON_DESCRIPTION,
                        ROLETTESERVICE_MAP_ZERO_BUTTON_DESCRIPTION,
                        ROLETTESERVICE_MAP_IMAGE_URL_START)
                .forEach(attr -> attributesToView.put(attr, rouletteMap.get(attr)));

        putParametrToMapIfNotNull(attributesToView, ROLETTESERVICE_MAP_IMAGE_URL_START, getImgViewFromMap(ROLETTESERVICE_MAP_IMAGE_URL_START));
    }

    private void fillFinishViewAttributes(Map<String, Object> attributesToView, String userAnswer, User user) {
        String rouletteColor = getResultOfRotation();
        String resultImgColorKey = ROLETTESERVICE_MAP_IMAGE_URL_ + rouletteColor;
        String resultImgColor = getImgViewFromMap(resultImgColorKey);
        putParametrToMapIfNotNull(attributesToView, GAME_ATTRIBUTE_IMAGE_URL, resultImgColor);

        String resulColor = ROLETTESERVICE_RESULT_COLOR_ + rouletteColor;
        String resultColorView = rouletteMap.get(resulColor);
        putParametrToMapIfNotNull(attributesToView, GAME_ATTRIBUTE_RESULT_COLOR,resultColorView);

        String resultInfo =
                (userAnswer.equals(rouletteColor))
                        ? rouletteMap.get(ROLETTESERVICE_MAP_RESULT_WIN)
                        : rouletteMap.get(ROLETTESERVICE_MAP_RESULT_LOSS);
        putParametrToMapIfNotNull(attributesToView, GAME_ATTRIBUTE_WIN_LOSS_DESCRIPTION, resultInfo);

        putParametrToMapIfNotNull(attributesToView, GAME_ATTRIBUTE_IS_DONE, true);

        if (user != null) {
            if (isWin(userAnswer,rouletteColor)) {
                userService.addUserWin(user, GAME_ROULETTE_NAME);
            } else {
                userService.addUserLoss(user, GAME_ROULETTE_NAME);
            }
        }
    }

    public String getResultOfRotation() {
        int randomNumber = random.nextInt(37);
        if (randomNumber < 18) {
            return ROLETTESERVICE_RED;
        } else if (randomNumber < 36) {
            return ROLETTESERVICE_BLACK;
        } else {
            return ROLETTESERVICE_ZERO;
        }
    }

    private String getImgViewFromMap(String imgKey) {
        String nameFile = rouletteMap.get(imgKey);
        return UrlHelper.createUrlFromFileName(nameFile);
    }

    public boolean isWin(String pickedColor, String rouletteColor) {
        return (pickedColor.equals(rouletteColor));
    }
}
