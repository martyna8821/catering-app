package pl.martyna.importer.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pl.martyna.importer.dto.IngredientToCreate;
import com.google.common.primitives.Ints;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class IngredientsFromJSONImporterService {

    private IngredientToCreate getSingleIngredient(){
        return null;
    }


    static int counter = 0;
    private Set<IngredientToCreate> getAllIngredients(){
        return null;
    }

    public Set<IngredientToCreate> importIngredientsFromJSON(String fileName){

        JSONParser jsonParser = new JSONParser();
        Set<IngredientToCreate> ingredients = new HashSet<>();


        try(FileReader reader = new FileReader(fileName)){

            Object obj = jsonParser.parse(reader);

            JSONArray ingredientsList = (JSONArray) obj;

            ingredientsList.forEach( ingredient -> parseIngredientObject((JSONObject) ingredient )
                                                        .ifPresent(parsedIngredient -> {ingredients.add(parsedIngredient);
                                                        })
            );

            ingredients.stream().forEach(ing -> System.out.println("Quantity: "+ing.getQuantity()));
            System.out.println(String.format("Liczba skladnikow: %d", counter));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Optional<IngredientToCreate> parseIngredientObject(JSONObject ingredient) {
            IngredientToCreate ingredientToCreate = new IngredientToCreate();

            String name = (String) ingredient.get("product_name");
            if(name.isBlank()){
                return Optional.empty();
            }
            else{
                ingredientToCreate.setName(name);
                counter++;
            }

            //System.out.println("INGREDIENT: " +ingredientToCreate.getName());
            String ingredientTypesString = (String) ingredient.get("categories");

            Stream.of(ingredientTypesString.split(","))
                    .forEach( ingredientType -> {
                                        if(ingredientType.startsWith("pl:"))
                                         {
                                                ingredientToCreate.addIngredientType(ingredientType.substring(3));
                                                System.out.println(ingredientType.substring(3));
                                        }
            });

            String[] strQuantity = ((String) ingredient.get("quantity")).split("[^-?0-9]+");
   //        switch(strQuantity.length)


        long quantity = Long.parseLong(strQuantity[0]);

        //String unit = strQuantity[1];
            System.out.println(String.format("Quantity:%d", quantity));
           // System.out.println("UNIT: "+ unit);
            ingredientToCreate.setQuantity(( int) quantity);

            return Optional.of(ingredientToCreate);
    }
}
