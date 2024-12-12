package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.QuestInfoEntity;

import java.util.*;

import static com.javarush.khmelov.repository.QuestConstants.*;

public class QuestRepository  {

    private final static List<QuestInfoEntity> questList = new LinkedList<>();

    public QuestRepository() {

        questList.add(new QuestInfoEntity(
                BUTTON_TEXT_LEFT_STATE_ONE,
                BUTTON_TEXT_RIGHT_STATE_ONE,
                RESULT_TEXT_MAP_STATE_ONE,
                DELTA_TIME_MAP_STATE_ONE,
                DELTA_EVIDENSE_MAP_STATE_ONE,
                DELTA_GOLD_MAP_STATE_ONE,
                DESCRIPTION_TEXT_STATE_ONE,
                IMAGE_URL_STATE_ONE
        ));




    }

    public List<QuestInfoEntity> getAll() {
        return questList;
    }

}
