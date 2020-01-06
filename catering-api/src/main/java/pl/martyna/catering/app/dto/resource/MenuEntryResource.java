package pl.martyna.catering.app.dto.resource;

import lombok.Data;

import java.util.UUID;

@Data
public class MenuEntryResource {

    private UUID menuEntryId;
    private String mealType;
    private RecipeResource recipe;
    private int amount;
}
