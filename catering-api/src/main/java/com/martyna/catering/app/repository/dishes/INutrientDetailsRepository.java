package com.martyna.catering.app.repository.dishes;

import com.martyna.catering.app.entity.dishes.NutrientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface INutrientDetailsRepository extends JpaRepository<NutrientDetails, UUID> {
}
