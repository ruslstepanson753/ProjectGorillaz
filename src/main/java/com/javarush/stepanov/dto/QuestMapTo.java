package com.javarush.stepanov.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestMapTo {
     String key;
     String value;
}
