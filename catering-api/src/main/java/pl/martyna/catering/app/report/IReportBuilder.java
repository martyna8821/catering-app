package pl.martyna.catering.app.report;

import java.time.LocalDate;

public interface IReportBuilder {

    void setReportDataDate(LocalDate reportDataDate);
    void buildMetaData();
    void buildReportData();
    void buildDataTable();
    void buildPdfDocument();
    Report getResult();
}
