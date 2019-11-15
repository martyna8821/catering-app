package pl.martyna.catering.app.service.menu.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.repository.menu.IMenuRepository;
import pl.martyna.catering.app.service.menu.IMenuEntryService;
import pl.martyna.catering.app.service.menu.IMenuService;

@Service
public class MenuService implements IMenuService {

    private IMenuRepository menuRepository;
    private IMenuEntryService menuEntryService;

    @Autowired
    public MenuService(IMenuRepository menuRepository, IMenuEntryService menuEntryService){

        this.menuRepository = menuRepository;
        this.menuEntryService = menuEntryService;
    }
}
