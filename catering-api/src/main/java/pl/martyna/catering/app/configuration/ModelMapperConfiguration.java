package pl.martyna.catering.app.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.martyna.catering.app.dto.input.DietInput;
import pl.martyna.catering.app.dto.input.MenuInput;
import pl.martyna.catering.app.dto.input.OrderInput;
import pl.martyna.catering.app.dto.input.RecipeIngredientInput;
import pl.martyna.catering.app.dto.resource.DietResource;
import pl.martyna.catering.app.dto.resource.IngredientResource;
import pl.martyna.catering.app.dto.resource.MenuResource;
import pl.martyna.catering.app.dto.resource.RecipeResource;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.entity.ingredient.MeasurementUnit;
import pl.martyna.catering.app.entity.menu.Menu;
import pl.martyna.catering.app.entity.order.Order;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;
import pl.martyna.catering.app.exception.UserNotFoundException;
import pl.martyna.catering.app.service.ingredient.IIngredientService;
import pl.martyna.catering.app.service.users.IUserService;
import pl.martyna.catering.app.service.users.impl.UserService;

import java.util.UUID;

@Configuration
public class ModelMapperConfiguration {


    @Autowired
    private IUserService userService;

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

        modelMapper.typeMap(IngredientResource.class, Ingredient.class)
                .setConverter(ctx -> this.ingredientService.getById(ctx.getSource().getId()));

        modelMapper.typeMap(String.class, User.class)
                .setConverter(ctx -> this.userService.findByUsername(ctx.getSource()));

        modelMapper.createTypeMap(Diet.class, DietResource.class)
                .addMapping(src -> src.getDietitian().getUsername(),
                            DietResource::setDietitianUsername);

        modelMapper.createTypeMap(RecipeIngredientInput.class, RecipeIngredient.class)
                .addMapping(src -> src,RecipeIngredient::setRecipe);


        modelMapper.createTypeMap(OrderInput.class, Order.class)
                .addMapping(OrderInput::getStartDate, Order::setStartDate)
                .addMapping(OrderInput::getEndDate, Order::setEndDate);

        modelMapper.createTypeMap(Recipe.class, RecipeResource.class)
                .addMapping(Recipe::getIngredients, RecipeResource::setIngredients)
                .addMapping(Recipe::getRecipeSteps, RecipeResource::setRecipeSteps);

      //  modelMapper.createTypeMap(MenuInput.class, Menu.class)

        modelMapper.createTypeMap(Menu.class, MenuResource.class)
                .addMapping(Menu::getMenuEntries, MenuResource::setMenuEntries);

        return modelMapper;
    }

}
