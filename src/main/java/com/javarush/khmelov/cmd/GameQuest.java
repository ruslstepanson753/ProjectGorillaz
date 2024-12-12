package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.QuestInfoEntity;
import com.javarush.khmelov.repository.QuestRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.util.LinkedList;
import java.util.List;

import static com.javarush.khmelov.repository.QuestConstants.LEFT;
import static com.javarush.khmelov.repository.QuestConstants.RIGHT;

@SuppressWarnings("unused")
public class GameQuest implements Command {

    private  List<QuestInfoEntity> questList ;
    private QuestInfoEntity conditionEntity;
    private Integer time;
    private Integer evidence;
    private Integer gold;
    private String pickedButton;

    public GameQuest(QuestRepository questRepository) {
        this.questList = questRepository.getAll();
    }

    @Override
    public String doGet(HttpServletRequest req) {
        setCondition(req);
        fillRequest(req);
        return getView();
    }

    private void setCondition(HttpServletRequest req){
        pickedButton = LEFT;
        pickedButton = req.getParameter("pickedButton");
        time = 5;
        evidence = 4;
        gold = 3;
        conditionEntity = questList.get(0);
    }

    private void fillRequest(HttpServletRequest req){
        req.setAttribute("buttonLeft", conditionEntity.getButtonLeftText());
        req.setAttribute("buttonRight", conditionEntity.getButtonRightText());
        String fullText = getFullText();
        req.setAttribute("fullText", fullText);
        req.setAttribute("time", time);
        req.setAttribute("evidence", evidence);
        req.setAttribute("gold", gold);
        req.setAttribute("imageUrl", conditionEntity.getImageUrl());


    }

    private String getFullText() {
        StringBuilder fullText = new StringBuilder();
        if (questList.indexOf(conditionEntity) != 0) {
            fullText.append(conditionEntity.getResultText(pickedButton));
            fullText.append("\n");
        }
        fullText.append(conditionEntity.getDescription());
        return fullText.toString();
    }


}
