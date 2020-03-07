package pl.martyna.catering.app.report;

public interface IReportBuilder {

    void buildMetaData();
    void buildReportData();
    void buildDataTable();
    void buildPdfDocument();
    Report getResult();
}
