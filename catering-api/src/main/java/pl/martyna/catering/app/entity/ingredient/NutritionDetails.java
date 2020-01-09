package pl.martyna.catering.app.entity.ingredient;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "nutrient_details")
public class NutritionDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "nutrient_details_id")
    UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "min_value")
    private int minValue;

    @Column(name = "max_value")
    private int maxValue;
}
