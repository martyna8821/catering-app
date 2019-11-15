package pl.martyna.catering.app.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;

@Getter
@Setter
@AllArgsConstructor
public class NutritionInput {

    private String englishName;
    private String polishName;
    private double value;
    private String unit;
}
