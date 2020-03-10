package pl.martyna.catering.app.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.martyna.catering.app.dto.resource.RecipeIngredientResource;
import pl.martyna.catering.app.dto.resource.RecipeStepResource;
import pl.martyna.catering.app.entity.menu.Menu;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.report.kitchen.IDailyReportBuilder;
import pl.martyna.catering.app.report.kitchen.report.KitchenRecipe;
import pl.martyna.catering.app.report.kitchen.report.MealCookingData;
import pl.martyna.catering.app.service.menu.IMenuService;
import pl.martyna.catering.app.service.order.IOrderService;

import java.time.LocalDate;
import java.util.*;
import java.util.List;

@Component
@Scope("prototype")
public class KitchenReportBuilder implements IDailyReportBuilder {

    private DailyReport report;
    private LocalDate reportDataDate;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

    @Qualifier("arial")
    @Autowired
    private BaseFont arialFont;

    @Qualifier("arial-bold")
    @Autowired
    private BaseFont arialBoldFont;

    private KitchenReportBuilder(IOrderService orderService,
                                 IMenuService menuService){
        this.menuService = menuService;
        this.orderService = orderService;
        this.report = new DailyReport();
    }

    @Override
    public void setReportDataDate(LocalDate reportDataDate){
        this.reportDataDate = reportDataDate;
    }
    @Override
    public void buildMetaData() {
        report.setTitle("Raport dla kuchni");
        report.setCreationDate(LocalDate.now());
    }

    @Override
    public void buildReportData() {
        List<Menu> createdMenus = this.menuService.getMenusFromDay(this.reportDataDate);
        Map<Recipe, MealCookingData> recipesCookingData = new HashMap<>();

        createdMenus.forEach( menu -> {
            int orderedMenusNumber = this.orderService.getOrderedMenusNumber(menu);
            if(orderedMenusNumber > 0 )
                menu.getMenuEntries().forEach( menuEntry -> {
                    if(!recipesCookingData.containsKey(menuEntry.getRecipe())){
                        recipesCookingData.put(menuEntry.getRecipe(), new MealCookingData());
                    }
                    recipesCookingData.get(menuEntry.getRecipe()).addPortions(orderedMenusNumber, menuEntry.getAmount());
                    recipesCookingData.get(menuEntry.getRecipe()).addWeight(orderedMenusNumber * menuEntry.getAmount());
                });
        });

        recipesCookingData.forEach( (recipe, cookingData) -> {
            cookingData.setKitchenRecipeName(recipe.getName());
            cookingData.setKitchenRecipeIngredients(recipe.getIngredients(), this.modelMapper);
            cookingData.setKitchenRecipeSteps(recipe.getRecipeSteps(), this.modelMapper);
            cookingData.calculateIngredientsWeight(recipe.getMealWeight());
        });

        this.report.setReportData(new ReportData<>(recipesCookingData));
    }

    @Override
    public void buildDataTable() {

       // this.report.setDataTable(dataTable);
    }

    @Override
    public void buildPdfDocument() {
        List<Element> list = new ArrayList();
        Font titleFont = new Font(arialBoldFont, 20);
        Font recipeBoldHeaderFont = new Font(arialBoldFont, 15);
        Font recipeHeaderFont = new Font(arialFont, 15);
        Font recipeFont = new Font(arialFont, 12);

        list.add(new Paragraph("Raport dla kuchni : " + this.reportDataDate+"\n\n", titleFont));

        this.report.getReportData().reportData.values().stream()
            .map(MealCookingData.class::cast)
            .forEach( cookingData -> {

                StringBuilder recipeHeaderString = new StringBuilder();
                recipeHeaderString.append("POSIŁEK: ");
                recipeHeaderString.append(((MealCookingData) cookingData).getKitchenRecipe().getName());
                recipeHeaderString.append(" ILOŚĆ: ");
                recipeHeaderString.append(((MealCookingData) cookingData).getWeight());
                recipeHeaderString.append("g\n");
                list.add(new Paragraph(recipeHeaderString.toString(), recipeBoldHeaderFont));

                ((MealCookingData) cookingData).getPortionsWeightNumberMap()
                     .forEach((weight, number) ->{
                         StringBuilder weightPortionsString = new StringBuilder();
                         weightPortionsString.append("* Liczba porcji o wadze ");
                         weightPortionsString.append(weight);
                         weightPortionsString.append("g : ");
                         weightPortionsString.append(number);
                         weightPortionsString.append("\n");
                         list.add(new Paragraph( weightPortionsString.toString() ,recipeHeaderFont));
                });

                list.add(new Paragraph(
                            this.getRecipeIngredientsString(((MealCookingData) cookingData).getKitchenRecipe()),
                            recipeFont));

                list.add(new Paragraph(
                        this.getRecipeStepsString(((MealCookingData) cookingData).getKitchenRecipe()),
                        recipeFont));
            });

        this.report.setPdfData(list);
    }

    @Override
    public Report getResult() {
        return this.report;
    }

    private String getRecipeIngredientsString(KitchenRecipe recipe){

        StringBuilder recipeIngredientsString = new StringBuilder();
        recipeIngredientsString.append("\n");
        recipe.getIngredients()
            .stream()
            .sorted(Comparator.comparingInt(RecipeIngredientResource::getValue)
                .reversed())
            .forEach(ingredient ->{
                recipeIngredientsString.append("\t - ");
                recipeIngredientsString.append(ingredient.getValue());
                recipeIngredientsString.append("g ");
                recipeIngredientsString.append(ingredient.getIngredient().getName());
                recipeIngredientsString.append("\n");
        });

        return recipeIngredientsString.toString();
    }


    private String getRecipeStepsString(KitchenRecipe recipe){

        StringBuilder recipeStepsString = new StringBuilder();
        recipe.getRecipeSteps().stream()
            .sorted(Comparator.comparingInt(RecipeStepResource::getStepNumber))
            .forEach(step -> {
                recipeStepsString.append("\n");
                recipeStepsString.append(step.getStepNumber());
                recipeStepsString.append(". ");
                recipeStepsString.append(step.getDescription());
                recipeStepsString.append("\n");
        });
        recipeStepsString.append("\n");

        return recipeStepsString.toString();
    }
}
