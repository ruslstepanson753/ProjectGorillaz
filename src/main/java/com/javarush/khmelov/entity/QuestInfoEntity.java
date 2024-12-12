package com.javarush.khmelov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestInfoEntity {

    private String buttonLeftText;

    private String buttonRightText;

    private Map<String,String> resultTexts;

    private Map<String,Integer> deltaTime;

    private Map<String,Integer> deltaEvidence;

    private Map<String,Integer> deltaGold;

    private String description;

    private String imageUrl;

    public String getResultText(String pickedButton){
        return resultTexts.get(pickedButton);
    }
}


