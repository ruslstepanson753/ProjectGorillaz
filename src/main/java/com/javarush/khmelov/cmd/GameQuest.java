package com.javarush.khmelov.cmd;

import com.javarush.khmelov.entity.QuestInfoEntity;
import com.javarush.khmelov.storage.quest.QuestRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import static com.javarush.khmelov.storage.ConstantsCommon.LEFT;

@SuppressWarnings("unused")
public class GameQuest implements Command {

    private  List<QuestInfoEntity> questList ;
    private QuestInfoEntity conditionEntity;
    private Integer time;
    private Integer evidence;
    private Integer gold;
    private String pickedButton;
    int step;

    public GameQuest(QuestRepository questRepository) {
        this.questList = questRepository.getAll();
    }

    @Override
    public String doGet(HttpServletRequest req) {
        String paramName = req.getParameter("pickedButton");
        if (paramName == null)
        setStartCondition(req);
        else {
            setCondition(req);
        }
        fillRequest(req);
        step++;
        return getView();
    }

    @Override
    public String doPost(HttpServletRequest req) {
        setCondition(req);
        fillRequest(req);
        return getView();
    }

    private void setStartCondition(HttpServletRequest req){
        pickedButton=LEFT;
        step=0;
        time = 5;
        evidence = 4;
        gold = 3;
        conditionEntity = questList.get(step);
    }

    private void setCondition(HttpServletRequest req){
        conditionEntity = questList.get(step);
        pickedButton = req.getParameter("pickedButton");
        time += conditionEntity.getDeltaTime(pickedButton) ;
        evidence += conditionEntity.getDeltaEvidence(pickedButton);
        gold += conditionEntity.getDeltaGold(pickedButton);
    }

    private void fillRequest(HttpServletRequest req){
        req.setAttribute("buttonLeft", conditionEntity.getButtonLeftText());
        req.setAttribute("buttonRight", conditionEntity.getButtonRightText());
        req.setAttribute("result", (pickedButton.equals(LEFT))
                ?conditionEntity.getResultLeftText()
                :conditionEntity.getResultRightText());
        req.setAttribute("resultRight", conditionEntity.getResultRightText());
        req.setAttribute("description", conditionEntity.getDescription());
        req.setAttribute("time", time);
        req.setAttribute("evidence", evidence);
        req.setAttribute("gold", gold);
        req.setAttribute("imageUrl", conditionEntity.getImageUrl());
    }




}
