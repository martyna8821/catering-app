package pl.martyna.catering.app.dto.resource;

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
    private Set<String> caloricVersions = new HashSet<>();
    private Set<String> labels = new HashSet<>();
    private String dietitianUsername;
   // byte[] image;
}
