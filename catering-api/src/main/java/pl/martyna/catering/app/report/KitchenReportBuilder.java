package pl.martyna.catering.app.report;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.martyna.catering.app.entity.menu.Menu;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.report.kitchen.report.KitchenRecipe;
import pl.martyna.catering.app.report.kitchen.report.MealCookingData;
import pl.martyna.catering.app.service.menu.IMenuService;
import pl.martyna.catering.app.service.order.IOrderService;
import pl.martyna.catering.app.service.recipe.IRecipeService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

@Component
@Scope("prototype")
public class KitchenReportBuilder implements IReportBuilder {

    private DailyReport report;
    private LocalDate reportDataDate;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ModelMapper modelMapper;

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

            menu.getMenuEntries().forEach( menuEntry -> {
                if(!recipesCookingData.containsKey(menuEntry.getRecipe())){
                    recipesCookingData.put(menuEntry.getRecipe(), new MealCookingData());
                }
                recipesCookingData.get(menuEntry.getRecipe()).addPortionsNumber(orderedMenusNumber);
                recipesCookingData.get(menuEntry.getRecipe()).addWeight(orderedMenusNumber * menuEntry.getAmount());
            });
        });

        recipesCookingData.forEach( (recipe, cookingData) -> {
            cookingData.setKitchenRecipeIngredients(recipe.getIngredients(), this.modelMapper);
            cookingData.setKitchenRecipeSteps(recipe.getRecipeSteps(), this.modelMapper);
            cookingData.calculateIngredientsWeight(recipe.getMealWeight());
        });

        this.report.setReportData(new ReportData<>(recipesCookingData));
    }

    @Override
    public void buildDataTable() {
        PdfPTable dataTable = new PdfPTable(3);
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    dataTable.addCell(header);
                });

        dataTable.addCell("row 1, col 1");
        dataTable.addCell("row 1, col 2");
        dataTable.addCell("row 1, col 3");
        this.report.setDataTable(dataTable);
    }

    @Override
    public void buildPdfDocument() {

    }

    @Override
    public Report getResult() {
        return this.report;
    }
}
