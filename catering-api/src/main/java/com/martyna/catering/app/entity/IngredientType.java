package com.martyna.catering.app.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.SecureRandom;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ingredient_type")
public class IngredientType {

    @Id
    @Column(name = "ingredient_typpe_id")
    private UUID id;

    @Column(name = "name")
    private String name;
}
