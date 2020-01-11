package pl.martyna.catering.app.service.diet.impl;

import org.modelmapper.ModelMapper;
import pl.martyna.catering.app.dto.input.DietInput;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.exception.ResourceNotFoundException;
import pl.martyna.catering.app.exception.UserNotFoundException;
import pl.martyna.catering.app.repository.diet.IDietRepository;
import pl.martyna.catering.app.service.diet.IDietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.service.users.IUserService;

import java.util.List;
import java.util.UUID;

@Service
public class DietService implements IDietService {

    private IDietRepository dietRepository;
    private IUserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public DietService(IDietRepository dietRepository,
                       IUserService userService ,ModelMapper modelMapper){
        this.dietRepository = dietRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @Override
    public Diet save(Diet dietToSave) {
        return this.dietRepository.save(dietToSave);
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

    @Override
    public void deleteById(UUID id) {
        this.dietRepository.deleteById(id);
    }

    @Override
    public void changeStatus(UUID id, boolean published) {
        this.dietRepository.changeStatus(id, published);
    }

    @Override
    public Diet addCaloricVersion(UUID id, String caloricVersion) {
         this.dietRepository.addCaloricVersion(id, caloricVersion);
         return  this.dietRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
