package com.martyna.catering.app.entity.diets;

import com.martyna.catering.app.entity.dishes.Recipe;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu_entries")
public class MenuEntry {

    @Id
    @Column(name = "menu_entry_id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Type( type = "pgsql_enum" )
    @Column(columnDefinition = "gender_t")

    
    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipe_id")
    private Recipe recipe;



}
