package pl.martyna.catering.app.dto.resource;

import lombok.Data;
import lombok.Setter;

import java.util.UUID;

@Data
public class IngredientResource {

    private UUID id;
    private String name;
    private String unit;
}
