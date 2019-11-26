package pl.martyna.catering.app.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.martyna.catering.app.dto.input.DietInput;
import pl.martyna.catering.app.dto.input.OrderInput;
import pl.martyna.catering.app.dto.resource.DietResource;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.entity.ingredient.MeasurementUnit;
import pl.martyna.catering.app.entity.order.Order;
import pl.martyna.catering.app.exception.UserNotFoundException;
import pl.martyna.catering.app.service.ingredient.IIngredientService;
import pl.martyna.catering.app.service.users.IUserService;

@Configuration
public class ModelMapperConfiguration {


    @Autowired
    private  IUserService userService;

    @Autowired
    private IIngredientService ingredientService;


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(Ingredient.class, String.class)
          .setConverter(ctx -> ctx.getSource().getName());
        modelMapper.typeMap(MeasurementUnit.class, String.class)
                .setConverter(ctx -> ctx.getSource().getAbbreviation());
        modelMapper.typeMap(String.class, Ingredient.class)
                .setConverter(ctx -> this.ingredientService.getByName(ctx.getSource()));

        modelMapper.typeMap(String.class, User.class)
                .setConverter(ctx -> this.userService.findByUsername(ctx.getSource()));

        modelMapper.createTypeMap(Diet.class, DietResource.class)
                .addMapping(src -> src.getDietitian().getUsername(),
                            DietResource::setDietitianUsername);

        modelMapper.createTypeMap(OrderInput.class, Order.class)
                .addMapping(OrderInput::getStartDate, Order::setStartDate)
                .addMapping(OrderInput::getEndDate, Order::setEndDate);
        return modelMapper;
    }

}
