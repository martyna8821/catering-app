package pl.martyna.catering.app.entity.recipe;


import pl.martyna.catering.app.entity.enums.DishType;
import pl.martyna.catering.app.entity.enums.PostgreSQLEnumType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    @Type( type = "pgsql_enum" )
    @Column(columnDefinition = "dish_type_t")
    private DishType dishType;

    @OneToMany
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Set<RecipeStep> recipeSteps;

}
