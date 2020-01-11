package pl.martyna.catering.app.repository.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pl.martyna.catering.app.entity.recipe.MealType;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IMealTypeRepository extends JpaRepository<MealType, UUID> {

    Optional<MealType> findAllByName(String name);
}
