package pl.martyna.catering.app.service.diet.impl;

import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.repository.diet.IDietRepository;
import pl.martyna.catering.app.service.diet.IDietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<Diet> getAll() {
        return this.dietRepository.findAll();
    }

    @Override
    public List<Diet> getById(UUID dietId) {
        return null;
    }

    @Override
    public boolean removeDietById(UUID dietId) {
        return false;
    }
}
