package com.martyna.catering.app.service.dishes;

import com.martyna.catering.app.entity.dishes.MeasurementUnit;
import com.martyna.catering.app.exception.ResourceNotFoundException;
import com.martyna.catering.app.repository.dishes.IMeasurementUnitRepository;
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
