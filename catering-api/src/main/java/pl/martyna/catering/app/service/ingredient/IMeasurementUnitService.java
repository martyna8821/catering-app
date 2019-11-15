package pl.martyna.catering.app.service.ingredient;

import pl.martyna.catering.app.entity.ingredient.MeasurementUnit;

public interface IMeasurementUnitService {

    public MeasurementUnit getUnitByAbbreviation(String  abbreviation);
}
