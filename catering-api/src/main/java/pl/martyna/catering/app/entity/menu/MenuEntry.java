package pl.martyna.catering.app.entity.menu;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "menu_entry_id")
    private UUID id;

    @Column(name = "meal_type")
    private String mealType;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Recipe recipe;



}
