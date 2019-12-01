package pl.martyna.catering.app.entity.recipe;


import pl.martyna.catering.app.entity.auth.Role;
import pl.martyna.catering.app.entity.enums.DishType;
import pl.martyna.catering.app.entity.enums.PostgreSQLEnumType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import pl.martyna.catering.app.entity.ingredient.Ingredient;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "recipes")
@TypeDef(name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class)
public class Recipe {

    @Id
    @Column(name = "recipe_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "meal_types", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(columnDefinition = "meal_type")
    private List<String> mealType;

    @ElementCollection
    @CollectionTable(name = "recipe_labels", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(columnDefinition = "recipe_labels")
    private List<String> labels;

    @Column(name = "meal_weight")
    private int mealWeight;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "recipe"
    )
    private Set<RecipeIngredient> ingredients = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Set<RecipeStep> recipeSteps;


}
