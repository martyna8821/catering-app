package pl.martyna.catering.app.entity.diet;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.entity.auth.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "diets")
public class Diet {

    @Id
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

    @ElementCollection
    @CollectionTable(name = "labels", joinColumns = @JoinColumn(name = "diet_id"))
    @Column(name = "labels")
    private Set<String> labels = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "dietitian_username", referencedColumnName = "username")
    private User dietitian;
}
