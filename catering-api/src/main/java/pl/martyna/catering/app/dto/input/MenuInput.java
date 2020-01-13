package pl.martyna.catering.app.dto.input;

import lombok.Data;
import lombok.Getter;
import pl.martyna.catering.app.dto.resource.DietResource;
import pl.martyna.catering.app.entity.menu.MenuEntry;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class MenuInput {

   private LocalDate menuDate;
   private DietResource diet;
   private String caloricVersion;
   private Set<MenuEntryInput> menuEntries = new HashSet<>();;

}
