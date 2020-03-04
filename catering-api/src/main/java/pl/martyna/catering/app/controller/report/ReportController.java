package pl.martyna.catering.app.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.martyna.catering.app.service.pdf.IPDFService;
import pl.martyna.catering.app.service.pdf.impl.PDFService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("api/reports")
public class ReportController {

    private IPDFService pdfService;

    @Autowired
    public ReportController(IPDFService pdfService){
        this.pdfService = pdfService;
    }

    @GetMapping("/hello-report")
    public ResponseEntity<?> getExamplePdf(){
        pdfService.createHelloPdf();
        return null;
    }
}
