package pl.martyna.catering.app.dto.resource;

import lombok.Data;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class IngredientResource {

    private UUID id;
    private String name;
    private String unit;
    private String caloricValue;
    private List<String> allergens = new ArrayList<>();
}
