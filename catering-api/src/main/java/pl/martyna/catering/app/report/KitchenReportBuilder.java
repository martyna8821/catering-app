package pl.martyna.catering.app.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.martyna.catering.app.entity.menu.Menu;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.report.kitchen.IDailyReportBuilder;
import pl.martyna.catering.app.report.kitchen.report.MealCookingData;
import pl.martyna.catering.app.service.menu.IMenuService;
import pl.martyna.catering.app.service.order.IOrderService;

import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.CheckedInputStream;

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
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
        Chunk title = new Chunk("Raport dla kuchni : " + this.reportDataDate+"\n", titleFont);
        list.add(title);
        list.add(Chunk.NEWLINE);
        list.add(Chunk.NEWLINE);

        Font recipeHeaderFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 15, BaseColor.BLACK);
        Font recipeFont = FontFactory.getFont(FontFactory.HELVETICA, 20, BaseColor.BLACK);

        this.report.getReportData().reportData.values().stream()
                .map(MealCookingData.class::cast)
                .forEach( cookingData ->{

                    StringBuilder recipeHeaderString = new StringBuilder();
                    recipeHeaderString.append("POSIŁEK: ");
                    recipeHeaderString.append(((MealCookingData) cookingData).getKitchenRecipe().getName());
                    recipeHeaderString.append(" ILOŚĆ: ");
                    recipeHeaderString.append(((MealCookingData) cookingData).getWeight());
                    recipeHeaderString.append("\n");
                    Paragraph p = new Paragraph();
                    p.add("this will be in bold \n");
                    list.add(p);
                    list.add(new Chunk( recipeHeaderString.toString(), recipeHeaderFont));


                    ((MealCookingData) cookingData).getPortionsWeightNumberMap().forEach((weight, number) ->{
                        StringBuilder weightPortionsString = new StringBuilder();
                        weightPortionsString.append("* ");
                        weightPortionsString.append(number);
                        weightPortionsString.append(" porcji o wadze ");
                        weightPortionsString.append(weight);
                        weightPortionsString.append("g\n");

                        list.add(new Chunk( weightPortionsString.toString() ,recipeHeaderFont));
                        list.add(Chunk.NEWLINE);
                    });
        });

        this.report.setPdfData(list);

    }

    @Override
    public Report getResult() {
        return this.report;
    }
}
