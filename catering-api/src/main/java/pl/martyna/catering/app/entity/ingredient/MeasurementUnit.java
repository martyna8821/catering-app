package pl.martyna.catering.app.entity.ingredient;

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
@Table(name = "measurement_units")
@NoArgsConstructor
public class MeasurementUnit implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "measurement_unit_id")
    UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "basic_value")
    private int basicValue;

    @Column(name= "abbreviation",
            length = 20)
    private String abbreviation;

}
