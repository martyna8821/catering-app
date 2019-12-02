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
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "recipes")
@TypeDef(name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class)
public class Recipe implements Serializable {

    @Id
    @Column(name = "recipe_id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(name = "meal_types", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(columnDefinition = "meal_type", name = "meal_type")
    private List<String> mealTypes = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "recipe_labels", joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(columnDefinition = "recipe_labels")
    private List<String> labels = new ArrayList<>();

    @Column(name = "meal_weight")
    private int mealWeight;

    @Column(name = "caloric_value")
    private double caloricValue;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "recipe"
    )
    private Set<RecipeIngredient> ingredients = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Set<RecipeStep> recipeSteps = new HashSet<>();


}
