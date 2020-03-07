package pl.martyna.catering.app.report.kitchen;

import pl.martyna.catering.app.report.IReportBuilder;

import java.time.LocalDate;

public interface IDailyReportBuilder extends IReportBuilder {
    void setReportDataDate(LocalDate reportDataDate);
}
