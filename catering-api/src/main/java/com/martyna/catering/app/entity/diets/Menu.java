package com.martyna.catering.app.entity.diets;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @Column(name = "menu_id")
    private UUID id;

    @OneToMany
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    private Set<MenuEntry> menuEntries;


}
