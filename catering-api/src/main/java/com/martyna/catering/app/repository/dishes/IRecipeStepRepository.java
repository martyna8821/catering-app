package com.martyna.catering.app.repository.dishes;

import com.martyna.catering.app.entity.dishes.RecipeStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRecipeStepRepository extends JpaRepository<RecipeStep, UUID> {
}
