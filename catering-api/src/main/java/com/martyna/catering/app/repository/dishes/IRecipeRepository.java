package com.martyna.catering.app.repository.dishes;

import com.martyna.catering.app.entity.dishes.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, UUID> {
}
