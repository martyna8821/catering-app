package pl.martyna.catering.app.entity.recipe;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "recipes")
public class Recipe implements Serializable {

    @Id
    @Column(name = "recipe_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable( name = "recipe_meal_types",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_type_id")
    )
    private Set<MealType> mealTypes = new HashSet<>();

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
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Set<RecipeStep> recipeSteps = new HashSet<>();

}
