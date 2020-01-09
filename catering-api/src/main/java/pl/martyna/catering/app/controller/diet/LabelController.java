package pl.martyna.catering.app.controller.diet;

import org.aspectj.asm.IModelFilter;
import org.dom4j.rule.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.martyna.catering.app.dto.input.LabelInput;
import pl.martyna.catering.app.entity.diet.Label;
import pl.martyna.catering.app.service.diet.ILabelService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/labels")
public class LabelController {

    private ILabelService labelService;
    private ModelMapper modelMapper;

    @Autowired
    public LabelController(ILabelService labelService, ModelMapper modelMapper){
        this.labelService = labelService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public Label saveLabel(@RequestBody LabelInput labelToSave){
        return this.labelService.save(modelMapper.map(labelToSave, Label.class));
    }

   // @GetMapping

    //DeleteMapping

}
