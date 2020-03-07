package pl.martyna.catering.app.report;

import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.interfaces.IAccessibleElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Report {

    private LocalDate creationDate;
    private String title;
    private ReportData<?> reportData;
    private PdfPTable dataTable;
    private List<Element> pdfData = new ArrayList<>();

    public String saveReport(){
        return null;
    }
}
