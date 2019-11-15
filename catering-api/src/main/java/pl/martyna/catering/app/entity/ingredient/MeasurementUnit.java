package pl.martyna.catering.app.entity.ingredient;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "measurement_units")
public class MeasurementUnit {

    @Id
    @Column(name = "measurement_unit_id")
    UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "basic_value")
    private int basicValue;

    @Column(name= "abbreviation")
    private String abbreviation;

}
