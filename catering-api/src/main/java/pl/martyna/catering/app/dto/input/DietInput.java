package pl.martyna.catering.app.dto.input;

import lombok.Data;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Data
public class DietInput {

    private String name;
    private String description;
    private int price;
    private Set<String> caloricVersions = new HashSet<>();
    private Set<String> forbiddenIngredients = new HashSet<>();
    private String dietitianUsername;
    private byte[] image;
}
