package com.martyna.catering.app.repository.dishes;

import com.martyna.catering.app.entity.dishes.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IIngredientTypeRepository extends JpaRepository<IngredientType, UUID> {
}
