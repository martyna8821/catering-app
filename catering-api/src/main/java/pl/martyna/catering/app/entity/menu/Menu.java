package pl.martyna.catering.app.entity.menu;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import pl.martyna.catering.app.dto.resource.DietResource;
import pl.martyna.catering.app.dto.resource.MenuEntryResource;
import pl.martyna.catering.app.entity.diet.Diet;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "menu")
public class Menu implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "menu_id")
    private UUID id;

    @Column(name = "menu_date")
    private LocalDate menuDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_id", referencedColumnName = "diet_id")
    private Diet diet;

    @Column(name = "caloric_version")
    private String caloricVersion;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    private Set<MenuEntry> menuEntries;

}
