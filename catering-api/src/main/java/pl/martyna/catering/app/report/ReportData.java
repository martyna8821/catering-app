package pl.martyna.catering.app.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Map;

@Getter @Setter @AllArgsConstructor
public class ReportData<T extends Map> {
    T reportData;
}
