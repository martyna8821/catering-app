package pl.martyna.catering.app.service.ingredient.impl;

import pl.martyna.catering.app.entity.ingredient.MeasurementUnit;
import pl.martyna.catering.app.exception.ResourceNotFoundException;
import pl.martyna.catering.app.repository.ingredient.IMeasurementUnitRepository;
import pl.martyna.catering.app.service.ingredient.IMeasurementUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementUnitService implements IMeasurementUnitService {

    private IMeasurementUnitRepository measurementUnitRepository;

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
