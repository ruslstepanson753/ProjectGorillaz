package com.javarush.stepanov.service;

import com.javarush.stepanov.entity.RouletteMap;
import com.javarush.stepanov.entity.User;
import com.javarush.stepanov.repository.RouletteMapRepository;
import com.javarush.stepanov.util.UrlHelper;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.javarush.stepanov.constants.ConstantsCommon.*;
public class RouletteService extends GameService {
    private final RouletteMapRepository rouletteMapRepository;
    private final Random random = new Random();
    private final Map<String, String> rouletteMap;
    private String rouletteColor;

    public RouletteService(RouletteMapRepository rouletteMapRepository,UserService userService) {
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

    public String[] getFinishInfo(String pickedColor) {
        String[] finishInfo = new String[3];

        rouletteColor = getResultOfRotation();
        String resultImgColor = ROLETTESERVICE_MAP_IMAGE_URL_ + rouletteColor;
        finishInfo[0] = getImgViewFromMap(resultImgColor);

        String resulColor = ROLETTESERVICE_RESULT_COLOR_ + rouletteColor;
        finishInfo[1] = rouletteMap.get(resulColor);

        finishInfo[2] =
                (pickedColor.equals(rouletteColor))
                        ? rouletteMap.get(ROLETTESERVICE_MAP_RESULT_WIN)
                        : rouletteMap.get(ROLETTESERVICE_MAP_RESULT_LOSS);

        return finishInfo;

    }

    public String[] getStartInfo() {
        String[] startInfo = new String[5];
        startInfo[0] = rouletteMap.get(ROLETTESERVICE_MAP_START_DESCRIPTION);
        startInfo[1] = rouletteMap.get(ROLETTESERVICE_MAP_RED_BUTTON_DESCRIPTION);
        startInfo[2] = rouletteMap.get(ROLETTESERVICE_MAP_BLACK_BUTTON_DESCRIPTION);
        startInfo[3] = rouletteMap.get(ROLETTESERVICE_MAP_ZERO_BUTTON_DESCRIPTION);
        startInfo[4] = getImgViewFromMap(ROLETTESERVICE_MAP_IMAGE_URL_START);
        return startInfo;
    }

    private String getImgViewFromMap(String imgKey) {
        String nameFile = rouletteMap.get(imgKey);
        return UrlHelper.createUrlFromFileName(nameFile);
    }

    public boolean isWin(String pickedColor) {
        return (pickedColor.equals(rouletteColor));
    }

    @Override
    Map<String, Object> processAttributes(String userAnswer, User user) {
        return Map.of();
    }

    @Override
    void fillViewAttributes(Map<String, Object> attributesToView) {

    }
}
