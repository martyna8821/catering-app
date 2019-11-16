package pl.martyna.catering.app.repository.ingredient;

import pl.martyna.catering.app.entity.ingredient.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IIngredientRepository extends JpaRepository<Ingredient, UUID> {

    Optional<Ingredient> findByName(String name);
}
