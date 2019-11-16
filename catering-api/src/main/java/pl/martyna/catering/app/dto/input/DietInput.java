package pl.martyna.catering.app.dto.input;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DietInput {

    private String name;
    private String description;
    private int price;
    private boolean published = false;
    private Set<String> caloricVersions = new HashSet<>();
    private Set<String> forbiddenIngredients = new HashSet<>();
    private Set<String> labels = new HashSet<>();
    private String dietitianUsername;
}
