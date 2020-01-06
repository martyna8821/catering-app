package pl.martyna.catering.app.service.menu;

import pl.martyna.catering.app.entity.menu.MenuEntry;

public interface IMenuEntryService {

    MenuEntry save(MenuEntry entryToSave);
}
