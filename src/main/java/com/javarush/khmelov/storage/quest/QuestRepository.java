package com.javarush.khmelov.storage.quest;

import com.javarush.khmelov.entity.QuestInfoEntity;

import java.util.*;

import static com.javarush.khmelov.storage.quest.ButtonText.*;
import static com.javarush.khmelov.storage.quest.Delta.*;
import static com.javarush.khmelov.storage.quest.Description.*;
import static com.javarush.khmelov.storage.quest.ImageUrl.*;
import static com.javarush.khmelov.storage.quest.ResultText.*;

public class QuestRepository  {

    private final static List<QuestInfoEntity> questList = new LinkedList<>();

    public QuestRepository() {

        questList.add(new QuestInfoEntity(
                BUTTON_TEXT_LEFT_STATE_ONE,
                BUTTON_TEXT_RIGHT_STATE_ONE,
                RESULT_TEXT_LEFT_STATE_ONE,
                RESULT_TEXT_RIGHT_STATE_ONE,
                DELTA_TIME_MAP_STATE_ONE,
                DELTA_EVIDENSE_MAP_STATE_ONE,
                DELTA_GOLD_MAP_STATE_ONE,
                DESCRIPTION_TEXT_STATE_ONE,
                IMAGE_URL_STATE_ONE
        ));

        questList.add(new QuestInfoEntity(
                BUTTON_TEXT_LEFT_STATE_TWO,
                BUTTON_TEXT_RIGHT_STATE_TWO,
                RESULT_TEXT_LEFT_STATE_TWO,
                RESULT_TEXT_RIGHT_STATE_TWO,
                DELTA_TIME_MAP_STATE_TWO,
                DELTA_EVIDENSE_MAP_STATE_TWO,
                DELTA_GOLD_MAP_STATE_TWO,
                DESCRIPTION_TEXT_STATE_TWO,
                IMAGE_URL_STATE_TWO
        ));

        questList.add(new QuestInfoEntity(
                BUTTON_TEXT_LEFT_STATE_THREE,
                BUTTON_TEXT_RIGHT_STATE_THREE,
                RESULT_TEXT_LEFT_STATE_THREE,
                RESULT_TEXT_RIGHT_STATE_THREE,
                DELTA_TIME_MAP_STATE_THREE,
                DELTA_EVIDENSE_MAP_STATE_THREE,
                DELTA_GOLD_MAP_STATE_THREE,
                DESCRIPTION_TEXT_STATE_THREE,
                IMAGE_URL_STATE_THREE
        ));

        questList.add(new QuestInfoEntity(
                BUTTON_TEXT_LEFT_STATE_FOUR,
                BUTTON_TEXT_RIGHT_STATE_FOUR,
                RESULT_TEXT_LEFT_STATE_FOUR,
                RESULT_TEXT_RIGHT_STATE_FOUR,
                DELTA_TIME_MAP_STATE_FOUR,
                DELTA_EVIDENSE_MAP_STATE_FOUR,
                DELTA_GOLD_MAP_STATE_FOUR,
                DESCRIPTION_TEXT_STATE_FOUR,
                IMAGE_URL_STATE_FOUR
        ));

        questList.add(new QuestInfoEntity(
                BUTTON_TEXT_LEFT_STATE_FIVE,
                BUTTON_TEXT_RIGHT_STATE_FIVE,
                RESULT_TEXT_LEFT_STATE_FIVE,
                RESULT_TEXT_RIGHT_STATE_FIVE,
                DELTA_TIME_MAP_STATE_FIVE,
                DELTA_EVIDENSE_MAP_STATE_FIVE,
                DELTA_GOLD_MAP_STATE_FIVE,
                DESCRIPTION_TEXT_STATE_FIVE,
                IMAGE_URL_STATE_FIVE
        ));

    }

    public List<QuestInfoEntity> getAll() {
        return questList;
    }

}
