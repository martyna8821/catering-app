package pl.martyna.catering.app.report;

import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter @Setter
public class Report {

    private LocalDate creationDate;
    private String title;
    private Map<?,?> reportData;
    private List<IBlockElement> pdfElements = new ArrayList<>();
}
