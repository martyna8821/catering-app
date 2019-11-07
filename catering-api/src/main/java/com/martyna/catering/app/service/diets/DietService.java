package com.martyna.catering.app.service.diets;

import com.martyna.catering.app.dto.DietDTO;
import com.martyna.catering.app.entity.diets.Diet;
import com.martyna.catering.app.repository.diets.IDietRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DietService implements IDietService {

    private IDietRepository dietRepository;


    @Autowired
    public DietService(IDietRepository dietRepository){
        this.dietRepository = dietRepository;
    }


    @Override
    public Diet save(Diet dietToSave) {
        return null;
    }
}
