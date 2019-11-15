package pl.martyna.catering.app.repository.ingredient;

import pl.martyna.catering.app.entity.ingredient.NutritionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface INutritionDetailsRepository extends JpaRepository<NutritionDetails, UUID> {
}
