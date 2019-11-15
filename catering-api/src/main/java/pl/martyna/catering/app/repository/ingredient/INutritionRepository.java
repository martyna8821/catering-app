package pl.martyna.catering.app.repository.ingredient;

import pl.martyna.catering.app.entity.ingredient.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface INutritionRepository extends JpaRepository<Nutrition, UUID> {

    //Nutrition save(Nutrition nutrition);
    Optional<Nutrition> getByName(String name);
}
