package com.martyna.catering.app.entity.diets;

import com.martyna.catering.app.entity.dishes.Ingredient;
import com.martyna.catering.app.entity.dishes.IngredientType;
import com.martyna.catering.app.entity.users.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.java.StringTypeDescriptor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "diets")
public class Diet {

    @Id
    @Column(name = "diet_id", columnDefinition = "uuid default uuid_generate_v4")
    private UUID id;

    @Column(name = "name")
    private  String name;

    @Column(name = "description")
    private String description;

    @Column(name = "weekly_price")
    private int price;

    @Column(name = "active")
    private boolean active;

    //@TODO kalorycznosc jakis enumik

    @ManyToMany
    Set<Ingredient> forbiddenIngredients;

    @ManyToMany
    Set<IngredientType> forbiddenIngredientTypes;

    @ManyToOne
    @JoinColumn(name = "dietitian_id", referencedColumnName = "user_id")
    private User dietitian;

}
