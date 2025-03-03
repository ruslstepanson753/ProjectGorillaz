package com.javarush.stepanov.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouletteMapTo {
     String key;
     String value;
}
