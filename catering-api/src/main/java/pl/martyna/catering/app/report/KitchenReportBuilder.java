package pl.martyna.catering.app.report;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.time.LocalDate;
import java.util.stream.Stream;

public class KitchenReportBuilder implements IReportBuilder {

    private Report report = new Report();

    @Override
    public void buildMetaData() {
        report.setTitle("Raport dla kuchni");
        report.setCreationDate(LocalDate.now());
        report.setReportDate(LocalDate.now());
    }

    @Override
    public void buildDataTable() {
        PdfPTable dataTable = new PdfPTable(3);
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    dataTable.addCell(header);
                });

        dataTable.addCell("row 1, col 1");
        dataTable.addCell("row 1, col 2");
        dataTable.addCell("row 1, col 3");
        this.report.setDataTable(dataTable);
    }

    @Override
    public Report getResult() {
        return this.report;
    }
}
