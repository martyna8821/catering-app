package pl.martyna.catering.app.service.menu.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.entity.menu.Menu;
import pl.martyna.catering.app.repository.menu.IMenuRepository;
import pl.martyna.catering.app.service.menu.IMenuEntryService;
import pl.martyna.catering.app.service.menu.IMenuService;

import java.util.List;
import java.util.UUID;

@Service
public class MenuService implements IMenuService {

    private IMenuRepository menuRepository;
    private IMenuEntryService menuEntryService;

    @Autowired
    public MenuService(IMenuRepository menuRepository, IMenuEntryService menuEntryService){

        this.menuRepository = menuRepository;
        this.menuEntryService = menuEntryService;
    }

    @Override
    public Menu save(Menu menuToSave) {

        menuToSave.getMenuEntries()
        return null;
    }

    @Override
    public List<Menu> getAll() {
        return this.menuRepository.findAll();
    }

    @Override
    public List<Menu> getDietsMenu(UUID dietId) {
        return this.menuRepository.findByDiet_Id(dietId);
    }
}
