package pl.martyna.catering.app.entity.diet;

import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.entity.auth.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "diets")
public class Diet {

    @Id
    @Column(name = "diet_id")
    private UUID id;

    @Column(name = "name")
    private  String name;

    @Column(name = "description")
    private String description;

    @Column(name = "weekly_price")
    private int price;

    @Column(name = "published")
    private boolean published;

    @ElementCollection
    @CollectionTable(name = "diets_caloric-version", joinColumns = @JoinColumn(name = "diet_id"))
    @Column(name = "caloric-version")
    private Set<String> caloricVersion = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "diets_forbidden-ingredients",
            joinColumns = @JoinColumn(name = "diet_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    Set<Ingredient> forbiddenIngredients;

    @ElementCollection
    @CollectionTable(name = "recipe_labels", joinColumns = @JoinColumn(name = "diet_id"))
    @Column(name = "labels")
    Set<String> labels;

    @ManyToOne
    @JoinColumn(name = "dietitian_id", referencedColumnName = "user_id")
    private User dietitian;

}
