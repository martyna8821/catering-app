package com.martyna.catering.app.entity.dishes;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "brands")
public class Brand {

    @Id
    @Column(name = "brand_id")
    private UUID id;

    @Column(name = "brand_name")
    private  String name;

}
