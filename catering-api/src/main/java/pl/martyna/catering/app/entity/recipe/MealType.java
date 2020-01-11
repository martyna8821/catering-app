package pl.martyna.catering.app.entity.recipe;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "meal_types")
@Data
public class MealType implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "meal_type_id")
    private UUID id;

    @Column(name = "name")
    private String name;
    
}
