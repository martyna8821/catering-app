package pl.martyna.catering.app.report;

import com.itextpdf.text.pdf.PdfPTable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Getter @Setter
public class Report {

    private LocalDate reportDate;
    private LocalDate creationDate;
    private String title;
    private PdfPTable dataTable;
}
