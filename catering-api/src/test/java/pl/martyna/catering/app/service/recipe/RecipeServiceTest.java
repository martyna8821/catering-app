package pl.martyna.catering.app.service.recipe;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.martyna.catering.app.entity.ingredient.Ingredient;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.entity.recipe.RecipeIngredient;
import pl.martyna.catering.app.service.recipe.impl.RecipeService;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecipeServiceTest {

     @TestConfiguration
        static class RecipeServiceImplTestContextConfiguration {

            @Bean
            public IRecipeService recipeService() {
                return new RecipeService();
            }
        }

        @Autowired
       private IRecipeService recipeService;

        private Recipe testRecipe;

    @Before
    public void createTestRecipe(){
        this.testRecipe = new Recipe();
        Set<RecipeIngredient> recipeIngredients = new HashSet<>();

        Ingredient ingredientMilk = new Ingredient("milk", "200");
        Ingredient ingredientBread = new Ingredient("bread", "100");
        recipeIngredients.add(new RecipeIngredient(ingredientMilk, this.testRecipe, 100));
        recipeIngredients.add(new RecipeIngredient(ingredientBread, this.testRecipe , 100));
        this.testRecipe.setIngredients(recipeIngredients);
    }

    @Test
    public void firstTestCalculateMealWeight(){
         this.testRecipe.setMealWeight(this.recipeService.calculateWeight(this.testRecipe.getIngredients()));
          assertThat(this.testRecipe.getMealWeight() == 200);
    }

    @Test
    public void testCalculateMealCaloricValue(){
        this.testRecipe.setCaloricValue(this.recipeService.calculateCaloricValue(
                                        this.testRecipe.getIngredients(),
                                    this.testRecipe.getMealWeight()
     ));
        assertThat(this.testRecipe.getCaloricValue() == 300);
    }
}
