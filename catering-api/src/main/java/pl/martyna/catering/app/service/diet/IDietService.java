package pl.martyna.catering.app.service.diet;

import pl.martyna.catering.app.dto.input.DietInput;
import pl.martyna.catering.app.entity.diet.Diet;

import java.util.List;
import java.util.UUID;

public interface IDietService {

    Diet save(Diet dietToSave);
    List<Diet> getAllDiets();
    void deleteById(UUID id);
    void changeStatus(UUID id, boolean published);
    Diet addCaloricVersion(UUID id, String caloricVersion);
}
