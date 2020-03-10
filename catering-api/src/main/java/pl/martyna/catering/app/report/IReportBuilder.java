package pl.martyna.catering.app.report;

import java.time.LocalDate;

public interface IReportBuilder {

    void buildMetaData();
    void buildReportData();
    void buildPdfElements();
    Report getResult();
}
