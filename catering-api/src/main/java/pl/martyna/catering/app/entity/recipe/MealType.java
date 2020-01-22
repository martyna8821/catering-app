package pl.martyna.catering.app.entity.recipe;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "meal_types")
@Data
@NoArgsConstructor
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

    @Column(name = "order_number")
    private int orderNumber;
    
}
