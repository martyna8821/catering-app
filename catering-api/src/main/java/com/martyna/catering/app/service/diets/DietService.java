package com.martyna.catering.app.service.diets;

import com.martyna.catering.app.repository.diets.IDietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietService implements IDietService {

    IDietRepository dietRepository;

    @Autowired
    public DietService(IDietRepository dietRepository){
        this.dietRepository = dietRepository;
    }


}
