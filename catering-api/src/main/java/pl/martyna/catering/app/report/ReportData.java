package pl.martyna.catering.app.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ReportData<T> {

    T reportData;
}
