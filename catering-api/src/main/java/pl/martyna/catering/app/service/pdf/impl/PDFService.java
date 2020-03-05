package pl.martyna.catering.app.service.pdf.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.report.IReportBuilder;
import pl.martyna.catering.app.report.KitchenReportBuilder;
import pl.martyna.catering.app.report.Report;
import pl.martyna.catering.app.report.ReportDirector;
import pl.martyna.catering.app.service.pdf.IPDFService;

import java.io.FileOutputStream;

//reports for kitchen and delivery guys

@Service
public class PDFService implements IPDFService {


    @Override
    public void createHelloPdf(){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

            ReportDirector director = new ReportDirector();
            IReportBuilder kitchenReportBuilder = new KitchenReportBuilder();
            director.constructReport(kitchenReportBuilder);
            Report kitchenReport = kitchenReportBuilder.getResult();

            document.add(kitchenReport.getDataTable());
        }catch (Exception ignored){

        }
        document.close();
    }
}
