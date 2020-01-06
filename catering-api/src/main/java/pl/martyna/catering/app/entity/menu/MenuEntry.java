package pl.martyna.catering.app.entity.menu;

import pl.martyna.catering.app.dto.resource.RecipeResource;
import pl.martyna.catering.app.entity.recipe.Recipe;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu_entries")
public class MenuEntry {

    @Id
    @Column(name = "menu_entry_id")
    private UUID id;

    @Column(name = "meal_type")
    private String mealType;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Recipe recipe;



}
