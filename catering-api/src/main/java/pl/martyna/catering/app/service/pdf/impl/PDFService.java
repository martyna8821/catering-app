package pl.martyna.catering.app.service.pdf.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.beans.BeansEndpoint;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.report.IReportBuilder;
import pl.martyna.catering.app.report.KitchenReportBuilder;
import pl.martyna.catering.app.report.Report;
import pl.martyna.catering.app.report.ReportDirector;
import pl.martyna.catering.app.service.pdf.IPDFService;

import java.io.FileOutputStream;
import java.time.LocalDate;

//reports for kitchen and delivery guys

@Service
public class PDFService implements IPDFService {

    @Autowired
    ApplicationContext context;


    @Override
    public void createHelloPdf(){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            ReportDirector director = new ReportDirector();
            KitchenReportBuilder kitchenReportBuilder = context.getBean(KitchenReportBuilder.class);
            kitchenReportBuilder.setReportDataDate(LocalDate.of(2020, 1, 20));

          //  IReportBuilder kitchenReportBuilder = new KitchenReportBuilder(LocalDate.of(2020, 1, 20));
            director.constructReport(kitchenReportBuilder);
            Report kitchenReport = kitchenReportBuilder.getResult();

            document.add(kitchenReport.getDataTable());
        }catch (Exception ignored){

        }
        document.close();
    }
}
