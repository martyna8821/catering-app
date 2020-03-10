package pl.martyna.catering.app.report.daily.kitchenreport;

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
import pl.martyna.catering.app.report.Report;
import pl.martyna.catering.app.report.daily.DailyReport;
import pl.martyna.catering.app.report.daily.IDailyReportBuilder;
import pl.martyna.catering.app.report.daily.kitchenreport.utils.KitchenReportRecipe;
import pl.martyna.catering.app.report.daily.kitchenreport.utils.MealCookingData;
import pl.martyna.catering.app.service.menu.IMenuService;
import pl.martyna.catering.app.service.order.IOrderService;

import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class KitchenReportBuilder implements IDailyReportBuilder {

    private DailyReport report;
    private LocalDate reportDataDate;
    private IMenuService menuService;
    private IOrderService orderService;
    private ModelMapper modelMapper;
    private BaseFont arialFont;
    private BaseFont arialBoldFont;

  //region Injection
    @Autowired
    public void setMenuService(IMenuService menuService){
        this.menuService = menuService;
    }

    @Autowired
    public void setOrderService(IOrderService orderService){
        this.orderService = orderService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Autowired
    @Qualifier("arial")
    public void setArialFont(BaseFont arialFont){
        this.arialFont = arialFont;
    }

    @Autowired
    @Qualifier("arial-bold")
    public void setArialBoldFont(BaseFont arialBoldFont){
        this.arialBoldFont = arialBoldFont;
    }

  //endregion

    private KitchenReportBuilder(){

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
                    recipesCookingData.get(menuEntry.getRecipe())
                            .addPortions(orderedMenusNumber, menuEntry.getAmount());
                    recipesCookingData.get(menuEntry.getRecipe())
                            .addWeight(orderedMenusNumber * menuEntry.getAmount());
                });
        });

        recipesCookingData.forEach( (recipe, cookingData) -> {
            cookingData.setKitchenRecipeName(recipe.getName());
            cookingData.setKitchenRecipeIngredients(recipe.getIngredients(), this.modelMapper);
            cookingData.setKitchenRecipeSteps(recipe.getRecipeSteps(), this.modelMapper);
            cookingData.calculateIngredientsWeight(recipe.getMealWeight());
        });

        this.report.setReportData(recipesCookingData);
    }

    @Override
    public void buildPdfElements() {

        List<Element> pdfElements = new ArrayList();
        Font titleFont = new Font(arialBoldFont, 20);
        Font recipeBoldHeaderFont = new Font(arialBoldFont, 15);
        Font recipeHeaderFont = new Font(arialFont, 15);
        Font recipeFont = new Font(arialFont, 12);

        pdfElements.add(new Paragraph("Raport dla kuchni : " + this.reportDataDate+"\n\n", titleFont));

        this.report.getReportData().values()
            .stream()
            .map(MealCookingData.class::cast)
            .forEach( cookingData -> {

                StringBuilder recipeHeaderString = new StringBuilder();
                recipeHeaderString.append("POSIŁEK: ");
                recipeHeaderString.append(cookingData.getKitchenReportRecipe().getName());
                recipeHeaderString.append(" ILOŚĆ: ");
                recipeHeaderString.append(cookingData.getWeight());
                recipeHeaderString.append("g\n");
                pdfElements.add(new Paragraph(recipeHeaderString.toString(), recipeBoldHeaderFont));

                pdfElements.add(new Paragraph(
                            this.getPortionsWeightHeaderString(cookingData),
                            recipeHeaderFont));

                pdfElements.add(new Paragraph(
                            this.getRecipeIngredientsString(cookingData.getKitchenReportRecipe()),
                            recipeFont));

                pdfElements.add(new Paragraph(
                            this.getRecipeStepsString(cookingData.getKitchenReportRecipe()),
                            recipeFont));
            });

        this.report.setPdfElements(pdfElements);
    }

    @Override
    public Report getResult() {
        return this.report;
    }

  //region PdfElementGenerators
    private String getPortionsWeightHeaderString(MealCookingData cookingData){

        StringBuilder weightPortionsString = new StringBuilder();
        cookingData.getPortionsWeightNumberMap()
                .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (weight, number) -> weight, HashMap::new))
                .forEach((weight, number) ->{
                    weightPortionsString.append("* Liczba porcji o wadze ");
                    weightPortionsString.append(weight);
                    weightPortionsString.append("g : ");
                    weightPortionsString.append(number);
                    weightPortionsString.append("\n");
                });

        return weightPortionsString.toString();
    }

    private String getRecipeIngredientsString(KitchenReportRecipe recipe){

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

    private String getRecipeStepsString(KitchenReportRecipe recipe){

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
  //endregion
}
