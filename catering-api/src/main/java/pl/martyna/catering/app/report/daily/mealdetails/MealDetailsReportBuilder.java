package pl.martyna.catering.app.report.daily.mealdetails;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.martyna.catering.app.entity.menu.Menu;
import pl.martyna.catering.app.entity.recipe.Recipe;
import pl.martyna.catering.app.report.Report;
import pl.martyna.catering.app.report.daily.DailyReport;
import pl.martyna.catering.app.report.daily.DailyReportBuilder;
import pl.martyna.catering.app.report.daily.IDailyReportBuilder;
import pl.martyna.catering.app.report.daily.kitchenreport.utils.MealCookingData;
import pl.martyna.catering.app.report.daily.mealdetails.utils.BoxDescriptionsData;
import pl.martyna.catering.app.service.menu.IMenuService;
import pl.martyna.catering.app.service.order.IOrderService;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.*;

@Component
@Scope("prototype")
public class MealDetailsReportBuilder
                    extends DailyReportBuilder
                    implements IDailyReportBuilder {

    private IMenuService menuService;
    private IOrderService orderService;

    //region Injection
    @Autowired
    public void setMenuService(IMenuService menuService){
        this.menuService = menuService;
    }

    @Autowired
    public void setOrderService(IOrderService orderService){
        this.orderService = orderService;
    }

    //endregion

     MealDetailsReportBuilder(){
        this.report = new DailyReport();
    }

    @Override
    public void setReportDataDate(LocalDate reportDataDate) {
        this.reportDataDate = reportDataDate;
    }

    @Override
    public void buildMetaData() {
        this.report.setTitle("Dane posiłków z dnia: " + this.report.getReportDataDate());
        this.report.setCreationDate(LocalDate.now());
    }

    @Override
    public void buildReportData() {
        List<Menu> createdMenus = this.menuService.getMenusFromDay(this.reportDataDate);
        Map<BoxDescriptionsData, Integer> boxDescriptionsDataMap = new HashMap<>();

        createdMenus.forEach( menu -> {
            int orderedMenusNumber = this.orderService.getOrderedMenusNumber(menu);
            if(orderedMenusNumber > 0 )
                menu.getMenuEntries().forEach( menuEntry -> {
                        boxDescriptionsDataMap.put(
                                modelMapper.map(menuEntry, BoxDescriptionsData.class),
                                orderedMenusNumber
                        );
                });
        });

        this.report.setReportData(boxDescriptionsDataMap);
    }

    @Override
    public void buildPdfElements() {
        List<Element> pdfElements = new ArrayList();
        Font titleFont = new Font(arialBoldFont, 20);
        Font dataFont = new Font(arialFont, 12);
        Font dataBoldFont = new Font(arialBoldFont, 12);

        pdfElements.add(new Paragraph(this.report.getTitle()+"\n\n", titleFont));

        for (Map.Entry<?, ?> entry : this.report.getReportData()
                .entrySet()) {
            Map.Entry<BoxDescriptionsData, Integer> boxDescriptionsDataIntegerEntry = (Map.Entry<BoxDescriptionsData, Integer>) entry;

            String boxDescriptionString = this.getBoxDescriptionString(boxDescriptionsDataIntegerEntry.getKey());
            for (int i = 0; i < boxDescriptionsDataIntegerEntry.getValue(); i++) {

                //Paragraph boxDescription = new Paragraph(boxDescriptionString, dataFont);
                //boxDescription.setBorder(new SolidBorder(1));
                //pdfElements.add();
                pdfElements.add(new DottedLineSeparator());
            }

            pdfElements.add(new Paragraph("\n"));
        }

        this.report.setPdfElements(pdfElements);
    }

    @Override
    public Report getResult() {
        return this.report;
    }


    private String getBoxDescriptionString(BoxDescriptionsData boxData) {
        StringBuilder boxDescriptionStringBuilder = new StringBuilder();
        boxDescriptionStringBuilder.append(boxData.getDietName());
        boxDescriptionStringBuilder.append(" ");
        boxDescriptionStringBuilder.append(boxData.getDietCaloricVersion());
        boxDescriptionStringBuilder.append("kcal \n");
        boxDescriptionStringBuilder.append("data dostarczenia: ");
        boxDescriptionStringBuilder.append(this.reportDataDate);
        boxDescriptionStringBuilder.append("\n");
        boxDescriptionStringBuilder.append(boxData.getMealName());
        boxDescriptionStringBuilder.append(": ");
        boxDescriptionStringBuilder.append(boxData.getMealName());
        boxDescriptionStringBuilder.append(" ");
        boxDescriptionStringBuilder.append(boxData.getMealCaloricValue());
        boxDescriptionStringBuilder.append("kcal \n");
        boxDescriptionStringBuilder.append("waga posiłku: ");
        boxDescriptionStringBuilder.append(boxData.getMealWeight());
        boxDescriptionStringBuilder.append("g \n\n");

        return boxDescriptionStringBuilder.toString();
    }

}
