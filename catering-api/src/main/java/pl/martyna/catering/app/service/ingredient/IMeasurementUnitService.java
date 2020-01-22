package pl.martyna.catering.app.service.ingredient;

import pl.martyna.catering.app.entity.ingredient.MeasurementUnit;

public interface IMeasurementUnitService {

    MeasurementUnit getUnitByAbbreviation(String abbreviation);
}
