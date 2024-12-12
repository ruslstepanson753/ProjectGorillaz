package com.javarush.khmelov.repository;

import java.util.Map;

public class QuestConstants {
    public static final String LEFT = "l";
    public static final String RIGHT = "r";

    protected static final String BUTTON_TEXT_LEFT_STATE_ONE = "левая кнопка 1";

    protected static final String BUTTON_TEXT_RIGHT_STATE_ONE = "правая кнопка 1";

    protected static final String RESULT_TEXT_LEFT_STATE_ONE = "результат слева 1";
    protected static final String RESULT_TEXT_RIGHT_STATE_ONE = "результат справа 1";

    protected static final String DESCRIPTION_TEXT_STATE_ONE = "описание 1";

    protected static final String IMAGE_URL_STATE_ONE = "адрес картинки1";

    protected static final Map<String, String> RESULT_TEXT_MAP_STATE_ONE = Map.of(
            LEFT, RESULT_TEXT_LEFT_STATE_ONE,
            RIGHT, RESULT_TEXT_RIGHT_STATE_ONE
    );

    protected static final Map<String, Integer> DELTA_TIME_MAP_STATE_ONE = Map.of(
            LEFT, 0,
            RIGHT, 0
    );

    protected static final Map<String, Integer> DELTA_EVIDENSE_MAP_STATE_ONE = Map.of(
            LEFT, 0,
            RIGHT, 0
    );

    protected static final Map<String, Integer> DELTA_GOLD_MAP_STATE_ONE = Map.of(
            LEFT, 0,
            RIGHT, 0
    );
}
