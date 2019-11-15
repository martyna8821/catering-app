package pl.martyna.catering.app.dto.diet;

import lombok.Data;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.entity.ingredient.Ingredient;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class DietResource {

    private UUID id;
    private String name;
    private String description;
    private int price;
    private boolean published;
    private Set<String> caloricVersion = new HashSet<>();
    private Set<String> forbiddenIngredients = new HashSet<>();
    private Set<String> labels = new HashSet<>();
    private String dietitianUsername;
}
