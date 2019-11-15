package pl.martyna.catering.app.controller.diet;

import io.micrometer.shaded.org.pcollections.AmortizedPQueue;
import org.springframework.http.HttpStatus;
import pl.martyna.catering.app.dto.diet.DietResource;
import pl.martyna.catering.app.dto.input.DietInput;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.service.diet.IDietService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/diets")
public class DietController {

    private IDietService dietService;
    private ModelMapper modelMapper;

    @Autowired
    public DietController(IDietService dietService, ModelMapper modelMapper){
        this.dietService = dietService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ResponseEntity<Diet> addDiet(@RequestBody DietInput dietToCreate){

        return null;
    }

    @GetMapping("")
    public List<DietResource> getAllDiets(){
        List<Diet> diets = this.dietService.getAll();
        return diets.stream()
                .map(diet -> modelMapper.map(diet, DietResource.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateDiet(@RequestBody UUID id){
     return null;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deteleById(@RequestBody UUID id){
        return null;
    }

}
