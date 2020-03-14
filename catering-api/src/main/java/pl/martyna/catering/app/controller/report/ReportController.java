package pl.martyna.catering.app.controller.report;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.martyna.catering.app.report.Report;
import pl.martyna.catering.app.service.report.IReportService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/reports")
public class ReportController {

    private IReportService reportService;

  //region Injection
    @Autowired
    public ReportController(IReportService reportService ){
        this.reportService = reportService;
    }
  //endregion

    @GetMapping(value = "/kitchen/{dataDate}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getKitchenDailyReport(HttpServletResponse response,
                                      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate dataDate)
                                throws DocumentException, IOException {

        Report generatedReport = this.reportService.getKitchenReport(dataDate);
        PdfDocument pdf = new PdfDocument(new PdfWriter(response.getOutputStream()));
        response.setContentType("application/pdf");
        Document document = new Document(pdf);

        generatedReport.getPdfElements()
                .forEach( document::add);

        document.close();
    }

    @GetMapping(value = "/box-descriptions/{dataDate}",
                produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getBoxDescriptionsDailyReport(HttpServletResponse response,
                                      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate dataDate)
            throws DocumentException, IOException {

        Report generatedReport = this.reportService.getBoxDescriptionsReport(dataDate);
        PdfDocument pdf = new PdfDocument(new PdfWriter(response.getOutputStream()));
        response.setContentType("application/pdf");
        Document document = new Document(pdf);

        generatedReport.getPdfElements()
                .forEach( document::add);

        document.close();
    }
}

