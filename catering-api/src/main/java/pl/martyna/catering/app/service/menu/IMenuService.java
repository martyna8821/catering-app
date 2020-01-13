package pl.martyna.catering.app.service.menu;

import pl.martyna.catering.app.entity.menu.Menu;

import java.util.List;
import java.util.UUID;

public interface IMenuService {

    Menu save(Menu menuToSave);
    List<Menu> getAll();
    List<Menu> getClientsMenus(UUID userId);
}
