package com.martyna.catering.app.service.ingredients.impl;

import com.martyna.catering.app.entity.ingredients.MeasurementUnit;
import com.martyna.catering.app.exception.ResourceNotFoundException;
import com.martyna.catering.app.repository.ingredients.IMeasurementUnitRepository;
import com.martyna.catering.app.service.dishes.IMeasurementUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementUnitService implements IMeasurementUnitService {

    IMeasurementUnitRepository measurementUnitRepository;

    @Autowired
    public MeasurementUnitService(IMeasurementUnitRepository measurementUnitRepository){
        this.measurementUnitRepository = measurementUnitRepository;
    }

    @Override
    public MeasurementUnit getUnitByAbbreviation(String abbreviation) {
        return this.measurementUnitRepository.getByAbbreviation(abbreviation)
                        .orElseThrow(ResourceNotFoundException::new);
    }
}
