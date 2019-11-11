package pl.martyna.importer.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import pl.martyna.importer.Nutrition;
import pl.martyna.importer.builder.Ingredient;

import java.awt.desktop.ScreenSleepEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class IngredientsToJSONExportService {

    public String writeIngredientsToJSONFile(Set<Ingredient> ingredients){

        JSONArray jsonIngredients = new JSONArray();

        ingredients.forEach(
                ingredient -> {
                    JSONObject jsonIngredient = new JSONObject();
                    jsonIngredient.put("name", ingredient.getName());
                    jsonIngredient.put("quantity", ingredient.getQuantity());
                    jsonIngredient.put("unit", ingredient.getUnit());

                    JSONArray jsonTypes = new JSONArray();
                    ingredient.getIngredientTypes().forEach(jsonTypes::add);
                    jsonIngredient.put("ingredientTypes", jsonTypes);

                    JSONArray jsonLabels = new JSONArray();
                    ingredient.getLabels().forEach(jsonLabels::add);
                    jsonIngredient.put("labels", jsonLabels);

                    JSONArray jsonBrands = new JSONArray();
                    ingredient.getBrands().forEach(jsonBrands::add);
                    jsonIngredient.put("brands", jsonBrands);

                    JSONArray jsonAllergens = new JSONArray();
                    ingredient.getAllergens().forEach(jsonAllergens::add);
                    jsonIngredient.put("allergens", jsonAllergens);

                    JSONArray jsonNutrition = new JSONArray();
                    for (Map.Entry<String, Nutrition> entry : ingredient.getNutrition().entrySet()) {
                        JSONObject nutritionObj = new JSONObject();
                        nutritionObj.put("englishName", entry.getKey());
                        nutritionObj.put("polishName", entry.getValue().getPolishName());
                        nutritionObj.put("value", entry.getValue().getValue());
                        nutritionObj.put("unit", entry.getValue().getUnit());
                        jsonNutrition.add(nutritionObj);
                    }
                    jsonIngredient.put("nutrition", jsonNutrition);
                    jsonIngredients.add(jsonIngredient);
                    System.out.println(String.format("Added %s", ingredient.getName()));
                });



        String fileName = "/home/martyna/git/api-catering/catering-app/import-database/src/main/resources/converted_data.json";
        try(FileWriter file = new FileWriter(fileName)){
            file.write(jsonIngredients.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
            fileName = "";
        }

        return fileName;
    }

}
