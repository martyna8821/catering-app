package pl.martyna.catering.app.repository.recipe;

import pl.martyna.catering.app.entity.recipe.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRecipeStepRepository extends JpaRepository<RecipeStep, UUID> {
}
