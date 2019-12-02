package pl.martyna.catering.app.dto.resource;

import lombok.Data;

import java.io.Serializable;

@Data
public class RecipeIngredientResource implements Serializable {

    private IngredientResource ingredient;
    private Integer value;

}
