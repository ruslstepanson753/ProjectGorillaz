package com.javarush.stepanov.dto;

import com.javarush.stepanov.entity.MapToJsonConverter;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class QuestInfoEntityTo {
     Long id;
     String buttonLeftText;
     String buttonRightText;
     String resultLeftText;
     String resultRightText;
     Map<String, Integer> deltaTime;
     Map<String, Integer> deltaEvidence;
     Map<String, Integer> deltaGold;
     String description;
     String imageUrl;
}
