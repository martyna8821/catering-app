package pl.martyna.catering.app.dto.input;

import lombok.Data;

@Data
public class NutritionInput {

    private String englishName;
    private String polishName;
    private double value;
    private String unit;
}
