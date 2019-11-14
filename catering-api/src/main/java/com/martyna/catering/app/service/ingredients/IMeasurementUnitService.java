package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.entity.ingredients.MeasurementUnit;

public interface IMeasurementUnitService {

    public MeasurementUnit getUnitByAbbreviation(String  abbreviation);
}
