package com.martyna.catering.app.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;

@Getter
@Setter
public class NutritionDTO {

    private String englishName;
    private String polishName;
    private double value;
    private String unit;
}
