package pl.martyna.catering.app.configuration;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
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

        modelMapper.typeMap(Ingredient.class, String.class)
          .setConverter(ctx -> ctx.getSource().getName());
        modelMapper.typeMap(MeasurementUnit.class, String.class)
                .setConverter(ctx -> ctx.getSource().getAbbreviation());

        modelMapper.createTypeMap(Diet.class, DietResource.class)
                .addMapping(src -> src.getDietitian().getUsername(),
                            DietResource::setDietitianUsername);

        return modelMapper;
    }

}
