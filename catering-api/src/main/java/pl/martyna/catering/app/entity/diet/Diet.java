package pl.martyna.catering.app.entity.diet;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.entity.auth.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diets")
public class Diet implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "diet_id")
    private UUID id;

    @Column(name = "name")
    private  String name;

    @Column(name = "description")
    private String description;

    @Column(name = "daily_price")
    private int price;

    @Column(name = "published")
    private boolean published = false;

    @ElementCollection
    @CollectionTable(name = "diets_caloric_version", joinColumns = @JoinColumn(name = "diet_id"))
    @Column(name = "caloric_version")
    private Set<String> caloricVersions = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "dietitian_username", referencedColumnName = "username")
    private User dietitian;

    public <E> Diet(UUID randomUUID, String test_diet, String description, int i, boolean b, Set<E> es) {
    }
}
