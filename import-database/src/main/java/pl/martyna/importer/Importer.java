package pl.martyna.importer;

import pl.martyna.importer.dto.IngredientToCreate;
import pl.martyna.importer.service.IngredientsFromJSONImporterService;

import java.util.Set;

public class Importer {


    public static void main(String[] args){
        IngredientsFromJSONImporterService importerService = new IngredientsFromJSONImporterService();

        Set<IngredientToCreate> ingredients = importerService.importIngredientsFromJSON("/home/martyna/git/api-catering/catering-app/import-database/src/main/resources/data.json");

    }


}
