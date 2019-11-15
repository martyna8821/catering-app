package pl.martyna.catering.app.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.martyna.catering.app.dto.diet.DietResource;
import pl.martyna.catering.app.dto.input.IngredientInput;
import pl.martyna.catering.app.dto.resource.IngredientResource;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.entity.ingredient.MeasurementUnit;

import java.util.stream.Collectors;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Ingredient.class, IngredientResource.class);
        modelMapper.createTypeMap(Diet.class, DietResource.class);
        modelMapper.getTypeMap(Ingredient.class, IngredientResource.class)
                     .addMappings(mapper -> {
                         mapper.map( src -> src.getMeasurementUnit().getAbbreviation(),
                         IngredientResource::setUnit);
                });

        modelMapper.getTypeMap(Diet.class, DietResource.class)
                .addMappings(mapper -> {
                    mapper.map( src -> src.getDietitian().getUsername(),
                            DietResource::setDietitianUsername);
                   // mapper.map(src -> src.getForbiddenIngredients().stream()
                     //                               .map(Ingredient::getName)
                       //                             .collect(Collectors.toSet()),
                        //    DietResource::setForbiddenIngredients);
                });

        return modelMapper;
    }

}
