package com.martyna.catering.app.entity;


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
@Table(name = "recipes")
public class Recipe {

    @Id
    @Column(name = "recipe_id")
    private UUID id;


    @Column(name = "name")
    private String name;

    

}
