package pl.martyna.catering.app.report.daily;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.kernel.font.PdfFont;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Scope("prototype")
public class DailyReportBuilder {

    protected DailyReport report;
    protected ModelMapper modelMapper;
    protected PdfFont arialFont;
    protected PdfFont arialBoldFont;

  //region Injection
    @Autowired
    public void setModelMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Autowired
    @Qualifier("arial")
    public void setArialFont(PdfFont arialFont){
        this.arialFont = arialFont;
    }

    @Autowired
    @Qualifier("arial-bold")
    public void setArialBoldFont(PdfFont arialBoldFont){
        this.arialBoldFont = arialBoldFont;
    }
  //endregion
}
