package com.martyna.catering.app.service.diets;

import com.martyna.catering.app.dto.DietDTO;
import com.martyna.catering.app.entity.diets.Diet;

public interface IDietService {

    Diet save(Diet dietToSave);
}
