package pl.martyna.catering.app.report.daily;

import lombok.Getter;
import lombok.Setter;
import pl.martyna.catering.app.report.Report;

import java.time.LocalDate;

@Getter @Setter
public class DailyReport extends Report {

    private LocalDate reportDataDate;
}
