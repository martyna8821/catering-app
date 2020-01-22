package pl.martyna.catering.app.entity.ingredient;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "ingredients")
@NoArgsConstructor
public class Ingredient implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "caloric_value",
            length = 50)
    private String caloricValue;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "measurement_unit_id", referencedColumnName = "measurement_unit_id")
    private MeasurementUnit measurementUnit;

    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @CollectionTable(name = "ingredient_allergens", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "allergens")
    private Set<String> allergens = new HashSet<>();

    @ElementCollection
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @CollectionTable(name = "ingredient_brands", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "brands")
    private Set<String> brands = new HashSet<>();

}
