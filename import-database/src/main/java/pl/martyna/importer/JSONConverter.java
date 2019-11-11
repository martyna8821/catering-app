package pl.martyna.importer;

import pl.martyna.importer.builder.Ingredient;
import pl.martyna.importer.service.IngredientsFromJSONImporterService;
import pl.martyna.importer.service.IngredientsToJSONExportService;

import java.util.Set;

public class JSONConverter {


    public static void main(String[] args){
        IngredientsFromJSONImporterService importerService = new IngredientsFromJSONImporterService();

        Set<Ingredient> ingredients = importerService.importIngredientsFromJSON("/home/martyna/git/api-catering/catering-app/import-database/src/main/resources/data.json");

        IngredientsToJSONExportService exportService = new IngredientsToJSONExportService();
        if(!exportService.writeIngredientsToJSONFile(ingredients).isBlank()){
            System.out.println("File successfully saved.");
        }

    }


}
