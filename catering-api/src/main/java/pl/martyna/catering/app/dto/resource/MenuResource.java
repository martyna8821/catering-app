package pl.martyna.catering.app.dto.resource;

import lombok.Data;
import pl.martyna.catering.app.dto.input.MenuEntryInput;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
public class MenuResource {

    private UUID id;
    private LocalDate menuDate;
    private DietResource diet;
    private String caloricVersion;
    private Set<MenuEntryResource> menuEntries;
}
