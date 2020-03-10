package pl.martyna.catering.app.report;

public class ReportDirector {

    public void constructReport(IReportBuilder builder){
        builder.buildMetaData();
        builder.buildReportData();
        builder.buildPdfElements();
    }
}
