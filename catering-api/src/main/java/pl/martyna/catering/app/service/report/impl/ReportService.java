package pl.martyna.catering.app.service.report.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.report.daily.kitchenreport.KitchenReportBuilder;
import pl.martyna.catering.app.report.Report;
import pl.martyna.catering.app.report.ReportDirector;
import pl.martyna.catering.app.report.daily.IDailyReportBuilder;
import pl.martyna.catering.app.service.report.IReportService;

import java.io.FileOutputStream;
import java.time.LocalDate;

//reports for kitchen and delivery guys

@Service
public class ReportService implements IReportService {

    private ApplicationContext context;

  //region Injection
    @Autowired
    public ReportService(ApplicationContext context) {
        this.context = context;
    }
    //endregion

    @Override
    public Report getKitchenReport(LocalDate dataDate) {

        ReportDirector director = new ReportDirector();
        IDailyReportBuilder kitchenReportBuilder = context.getBean(KitchenReportBuilder.class);
        kitchenReportBuilder.setReportDataDate(dataDate);
        director.constructReport(kitchenReportBuilder);

        return kitchenReportBuilder.getResult();
    }
}
