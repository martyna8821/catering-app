package pl.martyna.catering.app.entity.recipe;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "recipe_steps")
@NoArgsConstructor
public class RecipeStep implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "recipe_step_id")
    private UUID id;

    @Column(name = "step_number")
    private int stepNumber;

    @Column(name = "description")
    private String description;

}
