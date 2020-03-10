package pl.martyna.catering.app.controller.report;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
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

    @GetMapping(value = "/kitchen-report/{dataDate}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void getKitchenDailyReport(HttpServletResponse response,
                                      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate dataDate)
                                throws DocumentException, IOException {

        Report generatedReport = this.reportService.getKitchenReport(dataDate);
        Document document = new Document();
        response.setContentType("application/pdf");
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        generatedReport.getPdfElements()
                .forEach( element -> {
                    try {
                        document.add(element);
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                });
        document.close();
    }

}

