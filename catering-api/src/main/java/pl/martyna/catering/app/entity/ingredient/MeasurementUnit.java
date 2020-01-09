package pl.martyna.catering.app.entity.ingredient;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "measurement_units")
public class MeasurementUnit {

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

    @Column(name= "abbreviation")
    private String abbreviation;

}
