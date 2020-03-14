package pl.martyna.catering.app.report.daily;

import com.itextpdf.text.pdf.BaseFont;
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
    protected LocalDate reportDataDate;
    protected ModelMapper modelMapper;
    protected BaseFont arialFont;
    protected BaseFont arialBoldFont;

  //region Injection
    @Autowired
    public void setModelMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Autowired
    @Qualifier("arial")
    public void setArialFont(BaseFont arialFont){
        this.arialFont = arialFont;
    }

    @Autowired
    @Qualifier("arial-bold")
    public void setArialBoldFont(BaseFont arialBoldFont){
        this.arialBoldFont = arialBoldFont;
    }
  //endregion
}
