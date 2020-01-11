package pl.martyna.catering.app.controller.diet;

import org.springframework.http.HttpStatus;
import pl.martyna.catering.app.dto.resource.DietResource;
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
@CrossOrigin(origins = "*", maxAge = 3600)
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
    //@ResponseStatus(HttpStatus.CREATED)
    public Diet addDiet(@RequestBody DietInput dietToCreate){
        Diet diet = modelMapper.map(dietToCreate, Diet.class);
        return this.dietService.save(diet);
    }

    @GetMapping
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
    public ResponseEntity<?> deteleById(@PathVariable UUID id){
        this.dietService.removeDietById(id);
        return ResponseEntity.ok("Successfuly deleted");
    }

    @PatchMapping("/{id}/{published}")
    public ResponseEntity<?> changeStatus(@PathVariable UUID id, @PathVariable Boolean published){
        this.dietService.changeStatus(id, published);
        return ResponseEntity.ok("Status changed");
    }

    @PatchMapping("/{id}/caloric-version")
    public DietResource addCaloricVersion(@PathVariable UUID id, @RequestBody String caloricVersion){
       Diet changedDiet = this.dietService.addCaloricVersion(id, caloricVersion);
        return this.modelMapper.map(changedDiet, DietResource.class);
    }

}
