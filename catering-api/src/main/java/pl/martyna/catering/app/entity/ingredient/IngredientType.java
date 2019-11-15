package pl.martyna.catering.app.entity.ingredient;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ingredient_type")
public class IngredientType {

    @Id
    @Column(name = "ingredient_type_id")
    private UUID id;

    @Column(name = "name")
    private String name;
}
