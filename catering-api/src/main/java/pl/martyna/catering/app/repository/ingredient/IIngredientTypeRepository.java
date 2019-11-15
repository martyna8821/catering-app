package pl.martyna.catering.app.repository.ingredient;

import pl.martyna.catering.app.entity.ingredient.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IIngredientTypeRepository extends JpaRepository<IngredientType, UUID> {
}
