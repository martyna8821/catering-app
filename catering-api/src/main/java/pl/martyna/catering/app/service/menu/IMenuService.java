package pl.martyna.catering.app.service.menu;

import org.apache.tomcat.jni.Local;
import pl.martyna.catering.app.entity.menu.Menu;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IMenuService {

    Menu save(Menu menuToSave);
    List<Menu> getAll();
    List<Menu> getClientsMenus(UUID userId);
    List<Menu> getMenusFromDay(LocalDate menusDate);
}
