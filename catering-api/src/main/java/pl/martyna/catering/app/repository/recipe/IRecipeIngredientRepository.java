package pl.martyna.catering.app.repository.recipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;

import java.util.UUID;

@Repository
public interface IRecipeIngredientRepository extends JpaRepository<RecipeIngredient, UUID> {
}
