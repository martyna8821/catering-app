package pl.martyna.catering.app.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.martyna.catering.app.dto.auth.RegisterRequest;
import pl.martyna.catering.app.dto.input.*;
import pl.martyna.catering.app.dto.resource.*;
import pl.martyna.catering.app.entity.auth.Role;
import pl.martyna.catering.app.entity.auth.User;
import pl.martyna.catering.app.entity.diet.Diet;
import pl.martyna.catering.app.entity.menu.MenuEntry;
import pl.martyna.catering.app.entity.recipe.MealType;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.entity.ingredient.MeasurementUnit;
import pl.martyna.catering.app.entity.menu.Menu;
import pl.martyna.catering.app.entity.order.Order;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;
import pl.martyna.catering.app.report.daily.mealdetails.utils.BoxDescriptionsData;
import pl.martyna.catering.app.repository.auth.IRoleRepository;
import pl.martyna.catering.app.repository.ingredient.IMeasurementUnitRepository;
import pl.martyna.catering.app.repository.recipe.IMealTypeRepository;
import pl.martyna.catering.app.repository.recipe.IRecipeRepository;
import pl.martyna.catering.app.service.ingredient.IIngredientService;
import pl.martyna.catering.app.service.ingredient.IMeasurementUnitService;
import pl.martyna.catering.app.service.recipe.IMealTypeService;
import pl.martyna.catering.app.service.users.IUserService;

import java.util.UUID;

@Configuration
public class ModelMapperConfiguration {


    private IUserService userService;

    private IIngredientService ingredientService;

    private IMealTypeService mealTypeService;

    private IRoleRepository roleRepository;

    private IMeasurementUnitService measurementUnitService;

    private IRecipeRepository recipeRepository;

    @Autowired
    public ModelMapperConfiguration(IUserService userService, IIngredientService ingredientService, IMealTypeService mealTypeService, IRoleRepository roleRepository, IMeasurementUnitService measurementUnitService, IRecipeRepository recipeRepository) {
        this.userService = userService;
        this.ingredientService = ingredientService;
        this.mealTypeService = mealTypeService;
        this.roleRepository = roleRepository;
        this.measurementUnitService = measurementUnitService;
        this.recipeRepository = recipeRepository;
    }

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(Ingredient.class, String.class)
                          .setConverter(ctx -> ctx.getSource().getName());

        modelMapper.typeMap(String.class, MeasurementUnit.class)
                          .setConverter(ctx -> this.measurementUnitService.getUnitByAbbreviation(ctx.getSource()));

        modelMapper.typeMap(MeasurementUnit.class, String.class)
                          .setConverter(ctx -> "g");

        modelMapper.typeMap(String.class, Ingredient.class)
                          .setConverter(ctx -> this.ingredientService.getByName(ctx.getSource()));

        modelMapper.typeMap(IngredientResource.class, Ingredient.class)
                          .setConverter(ctx -> this.ingredientService.getById(ctx.getSource().getId()));

        modelMapper.typeMap(String.class, User.class)
                          .setConverter(ctx -> this.userService.findByUsername(ctx.getSource()));

        modelMapper.typeMap(String.class, MealType.class)
                          .setConverter(ctx -> this.mealTypeService.findByName(ctx.getSource()));

        modelMapper.createTypeMap(String.class, Role.class)
                                .setConverter(ctx -> this.roleRepository.findByRole(ctx.getSource()).get());

        modelMapper.typeMap(RecipeResource.class, Recipe.class)
                          .setConverter(ctx -> this.recipeRepository.findById(UUID.fromString(ctx.getSource().getId())).get());

        modelMapper.createTypeMap(RecipeIngredientInput.class, RecipeIngredient.class)
                .addMapping(src -> src,RecipeIngredient::setRecipe);

        modelMapper.createTypeMap(RecipeIngredientResource.class, RecipeIngredient.class)
                .addMapping(src -> src,RecipeIngredient::setRecipe);

        modelMapper.createTypeMap(OrderInput.class, Order.class)
                .addMapping(OrderInput::getStartDate, Order::setStartDate)
                .addMapping(OrderInput::getEndDate, Order::setEndDate);

        modelMapper.createTypeMap(Recipe.class, RecipeResource.class)
                .addMapping(Recipe::getIngredients, RecipeResource::setIngredients)
                .addMapping(Recipe::getRecipeSteps, RecipeResource::setRecipeSteps);

        modelMapper.createTypeMap(MenuInput.class, Menu.class)
                .addMapping(MenuInput::getMenuEntries, Menu::setMenuEntries);

        modelMapper.createTypeMap(Menu.class, MenuResource.class)
                .addMapping(Menu::getMenuEntries, MenuResource::setMenuEntries);

        modelMapper.createTypeMap(IngredientInput.class, Ingredient.class)
                .addMapping(IngredientInput::getAllergens, Ingredient::setAllergens)
                .addMapping(IngredientInput::getBrands, Ingredient::setBrands);

        modelMapper.createTypeMap(MenuEntry.class, BoxDescriptionsData.class)
                .addMapping(MenuEntry::getCaloricValue, BoxDescriptionsData::setMealCaloricValue)
                .addMapping(src -> src.getMealType().getName(), BoxDescriptionsData::setMealType)
                .addMapping(src -> src.getRecipe().getName(), BoxDescriptionsData::setMealName)
                .addMapping(MenuEntry::getAmount, BoxDescriptionsData::setMealWeight)
                .addMapping(src -> src.getMenu().getCaloricVersion(), BoxDescriptionsData::setDietCaloricVersion)
                .addMapping(src -> src.getMenu().getDiet().getName(), BoxDescriptionsData::setDietName);

        return modelMapper;
    }

}
