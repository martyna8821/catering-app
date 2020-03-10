package pl.martyna.catering.app.service.report;

import pl.martyna.catering.app.report.Report;
import pl.martyna.catering.app.report.daily.DailyReport;

import java.time.LocalDate;

public interface IReportService {

    Report getKitchenReport(LocalDate dataDate);
}
