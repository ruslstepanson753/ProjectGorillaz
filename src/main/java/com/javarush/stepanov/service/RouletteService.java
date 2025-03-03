package com.javarush.stepanov.service;

import com.javarush.stepanov.constants.ConstantsCommon;
import com.javarush.stepanov.dto.UserTo;
import com.javarush.stepanov.entity.RouletteMap;
import com.javarush.stepanov.repository.RouletteMapRepository;
import com.javarush.stepanov.util.UrlHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.javarush.stepanov.constants.ConstantsCommon.*;

public class RouletteService extends GameService {
    private final RouletteMapRepository rouletteMapRepository;
    private final Random random = new Random();
    private final Map<String, String> rouletteMap;

    public RouletteService(RouletteMapRepository rouletteMapRepository,UserService userService) {
        super(userService);
        this.rouletteMapRepository = rouletteMapRepository;
        this.rouletteMap = getRoulletteMap();
    }

    private Map<String, String> getRoulletteMap() {
        Map<String, String> map = new HashMap<String, String>();
        List<RouletteMap> rouletteMapList = (List<RouletteMap>) rouletteMapRepository.getAll();
        rouletteMapList.forEach(questMap -> {
            map.put(questMap.getKey(), questMap.getValue());
        });
        return map;
    }

    @Override
    public Map<String, Object> processAttributes(String userAnswer, UserTo userTo) {
        Map<String, Object> attributesToView = new HashMap<>();
        if (userAnswer == null) {
            fillViewAttributes(attributesToView);
        } else {
            fillFinishViewAttributes(attributesToView, userAnswer, userTo);
        }

        return attributesToView;
    }

    @Override
    protected void fillViewAttributes(Map<String, Object> attributesToView) {
        List.of(KEY_START_DESCRIPTION,
                        KEY_RED_BUTTON_DESCRIPTION,
                        ConstantsCommon.KEY_BLACK_BUTTON_DESCRIPTION,
                        ConstantsCommon.ROULET_ZERO_BUTTON_DESCRIPTION,
                        ConstantsCommon.KEY_IMAGE_URL_START)
                .forEach(attr -> attributesToView.put(attr, rouletteMap.get(attr)));

        putParametrToMapIfNotNull(attributesToView, ConstantsCommon.KEY_IMAGE_URL_START, getImgViewFromMap(ConstantsCommon.KEY_IMAGE_URL_START));
    }

    private void fillFinishViewAttributes(Map<String, Object> attributesToView, String userAnswer, UserTo user) {
        String rouletteColor = getResultOfRotation();
        String resultImgColorKey = ROULET_MAP_IMAGE_URL_ + rouletteColor;
        String resultImgColor = getImgViewFromMap(resultImgColorKey);
        putParametrToMapIfNotNull(attributesToView, ATTR_IMG_URL, resultImgColor);

        String resulColor = ROULET_RESULT_COLOR_ + rouletteColor;
        String resultColorView = rouletteMap.get(resulColor);
        putParametrToMapIfNotNull(attributesToView, ATTR_RESULT_COLOR,resultColorView);

        String resultInfo =
                (userAnswer.equals(rouletteColor))
                        ? rouletteMap.get(ROULET_MAP_RESULT_WIN)
                        : rouletteMap.get(ROULET_MAP_RESULT_LOSS);
        putParametrToMapIfNotNull(attributesToView, ATTR_WIN_LOSS_DESCRIPTION, resultInfo);

        putParametrToMapIfNotNull(attributesToView, ConstantsCommon.ATTR_IS_DONE, true);

        if (user != null) {
            if (isWin(userAnswer,rouletteColor)) {
                userService.addUserWin(user, GAME_ROULETTE_NAME);
            } else {
                userService.addUserLoss(user, GAME_ROULETTE_NAME);
            }
        }
    }

    protected String getResultOfRotation() {
        int randomNumber = random.nextInt(37);
        if (randomNumber < 18) {
            return ROULET_RED;
        } else if (randomNumber < 36) {
            return ROULET_BLACK;
        } else {
            return ROULET_ZERO;
        }
    }

    private String getImgViewFromMap(String imgKey) {
        String nameFile = rouletteMap.get(imgKey);
        return UrlHelper.createUrlFromFileName(nameFile);
    }

    private boolean isWin(String pickedColor, String rouletteColor) {
        return (pickedColor.equals(rouletteColor));
    }
}
