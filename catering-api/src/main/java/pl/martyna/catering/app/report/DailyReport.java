package pl.martyna.catering.app.report;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class DailyReport extends Report {

    private LocalDate reportDataDate;
}
