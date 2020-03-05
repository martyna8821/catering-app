package pl.martyna.catering.app.report;

public interface IReportBuilder {

    void buildMetaData();
    void buildDataTable();
    Report getResult();
}
