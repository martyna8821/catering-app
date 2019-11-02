package com.martyna.catering.app.entity.dishes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "recipe_steps")
public class RecipeStep {

    @Id
    @Column(name = "recipe_step_id")
    private UUID id;

    @Column(name = "step_number")
    private int stepNumber;

    @Column(name = "description")
    private String description;

}
