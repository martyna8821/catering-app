package com.martyna.catering.app.repository.ingredients;

import com.martyna.catering.app.entity.ingredients.NutritionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface INutritionDetailsRepository extends JpaRepository<NutritionDetails, UUID> {
}
