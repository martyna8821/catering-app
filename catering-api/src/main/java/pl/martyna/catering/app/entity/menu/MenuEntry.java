package pl.martyna.catering.app.entity.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import pl.martyna.catering.app.dto.resource.RecipeResource;
import pl.martyna.catering.app.entity.recipe.MealType;
import pl.martyna.catering.app.entity.recipe.Recipe;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu_entries")
@NoArgsConstructor
public class MenuEntry implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "menu_entry_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "meal_type_id", referencedColumnName = "meal_type_id")
    private MealType mealType;

    @Column(name = "amount")
    private int amount;

    @Column(name = "caloric_value")
    private String caloricValue;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Recipe recipe;

}
