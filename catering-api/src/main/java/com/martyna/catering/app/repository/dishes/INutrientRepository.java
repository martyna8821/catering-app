package com.martyna.catering.app.repository.dishes;

import com.martyna.catering.app.entity.dishes.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface INutrientRepository extends JpaRepository<Nutrition, UUID> {

    //Nutrition save(Nutrition nutrition);
    Optional<Nutrition> getByName(String name);
}
