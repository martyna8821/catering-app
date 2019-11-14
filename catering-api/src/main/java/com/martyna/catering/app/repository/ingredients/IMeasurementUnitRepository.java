package com.martyna.catering.app.repository.ingredients;

import com.martyna.catering.app.entity.ingredients.MeasurementUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IMeasurementUnitRepository extends JpaRepository<MeasurementUnit, UUID> {

    Optional<MeasurementUnit> getByAbbreviation(String abbreviation);
}
