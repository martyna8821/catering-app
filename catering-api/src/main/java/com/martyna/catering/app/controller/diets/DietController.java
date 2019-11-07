package com.martyna.catering.app.controller.diets;

import com.martyna.catering.app.dto.DietDTO;
import com.martyna.catering.app.entity.diets.Diet;
import com.martyna.catering.app.repository.diets.IDietRepository;
import com.martyna.catering.app.service.diets.IDietService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("diet")
public class DietController {

    private IDietService dietService;
    private ModelMapper modelMapper;

    @Autowired
    public DietController(IDietService dietService, ModelMapper modelMapper){
        this.dietService = dietService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    ResponseEntity<Diet> addDiet(@RequestBody DietDTO dietToCreate){

        //modelMapper.getTypeMap().getConverter().convert();
        //Diet createdDiet = this.dietService.save(convertToEntitt
       // )
        return null;
    }
}
