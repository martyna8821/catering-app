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

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping
    public ResponseEntity<?> addDiet(@RequestBody DietInput dietToCreate){
        Diet diet = modelMapper.map(dietToCreate, Diet.class);
        return new ResponseEntity<>(this.dietService.save(diet), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllDiets(){
        List<DietResource> diets = this.dietService.getAllDiets()
                                                    .stream()
                                                    .map(diet ->
                                                            modelMapper.map(diet, DietResource.class))
                                                    .collect(Collectors.toList());

        return new ResponseEntity<>(diets, HttpStatus.OK);
    }

    @PatchMapping("/{id}/{published}")
    public ResponseEntity<?> changeStatus(@PathVariable UUID id, @PathVariable Boolean published){
        this.dietService.changeStatus(id, published);
        return new ResponseEntity<>("Status changed", HttpStatus.OK);
    }

    @PatchMapping("/{id}/caloric-version")
    public ResponseEntity<?> addCaloricVersion(@PathVariable UUID id, @RequestBody String caloricVersion){
        Diet changedDiet = this.dietService.addCaloricVersion(id, caloricVersion);

        return new ResponseEntity<>( this.modelMapper.map( changedDiet, DietResource.class),
                                     HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){
        this.dietService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
